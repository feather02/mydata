package com.project.exam_system.controller;

import com.project.exam_system.dto.ExamSubmissionDTO;
import com.project.exam_system.dto.QuestionForm;
import com.project.exam_system.entity.*;
import com.project.exam_system.service.ExamService;
import com.project.exam_system.service.QuestionService;
import com.project.exam_system.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ResultService resultService;

    @GetMapping("/create")
    public ModelAndView createExamPage() {
        ModelAndView modelAndView = new ModelAndView("createExam");
        modelAndView.addObject("exam", new Exam());
        return modelAndView;
    }

    @PostMapping("/create")
    public String createExam(@ModelAttribute("exam") Exam exam, HttpSession session, Model model) {
        Faculty faculty = (Faculty) session.getAttribute("loggedFaculty");
        if (faculty == null) {
            return "redirect:/faculty/login"; // Redirect if not logged in
        }
        exam.setFaculty(faculty);  // Assign faculty to exam
        examService.createExam(exam);
        return "redirect:/addQuestions?examId=" + exam.getExamId() + "&noOfQuestions=" + exam.getNoOfQuestions();
    }

    @GetMapping("/addQuestions")
    public String addQuestionsPage(HttpSession session, @RequestParam int examId, @RequestParam int noOfQuestions, Model model) {
        Faculty faculty = (Faculty) session.getAttribute("loggedFaculty");
        if (faculty == null) {
            return "redirect:/faculty/login";
        }

        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return "errorPage";  // Handle invalid exam
        }
        QuestionForm questionForm = new QuestionForm();
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < noOfQuestions; i++) {
            Question question = new Question();
            question.setQuestionNumber(i + 1);
            questions.add(question);
        }
        questionForm.setQuestionList(questions);
        model.addAttribute("questionForm", questionForm);
        model.addAttribute("examId", examId);
        model.addAttribute("noOfQuestions", noOfQuestions);
        return "addQuestions";
    }


    @PostMapping("/submitQuestions")
    public String addQuestions(@RequestParam int examId, @ModelAttribute("questionForm") QuestionForm questionForm) {
        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return "errorPage";  // Handle invalid exam
        }
        for (Question question : questionForm.getQuestionList()) {
            question.setExam(exam);  // Link question to exam
            // Ensure questionNumber is set correctly if needed
            if (question.getQuestionNumber() == 0) {
                int nextQuestionNumber = questionService.getNextQuestionNumberForExam(examId);
                question.setQuestionNumber(nextQuestionNumber);
            }
            questionService.addQuestion(question);
        }
        return "redirect:/facultyDashboard";
    }

    @Transactional
    @GetMapping("/changeStatus")
    public String changeStatus(@RequestParam("id") int id,@RequestParam("username") String username) {
        Exam exam = examService.getExamById(id);
        Faculty faculty = exam.getFaculty();
        if (exam.getStatus()) {
            examService.updateStatus(id,false);
        } else {
            examService.updateStatus(id,true);
        }
        return "redirect:/facultyDashboard";
    }

    @GetMapping("/attendExam/{examId}")
    public String attendExam(HttpSession session,@PathVariable int examId, Model model) {
        Student student = (Student) session.getAttribute("loggedStudent");

        if (student == null) {
            return "redirect:/student/login"; // Redirect if not logged in
        }

        Boolean alreadyDone =resultService.getAlreadyDone(examId,student.getRollNo());
        if(alreadyDone) {
            return "examAlreadyDone";
        }

        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return "errorPage";
        }

        List<Question> questionSet = questionService.getQuestionsByExamId(examId);
        ExamSubmissionDTO examSubmission = new ExamSubmissionDTO();
        examSubmission.setExamId(examId);

        model.addAttribute("examId", examId);
        model.addAttribute("questionSet", questionSet);
        model.addAttribute("examSubmission", examSubmission);

        return "attendExam";
    }


    @PostMapping("/submitExam")
    public String submitExam(@ModelAttribute ExamSubmissionDTO examSubmission,
                             HttpSession session,
                             Model model) {

        Student student = (Student) session.getAttribute("loggedStudent");
        if (student == null) {
            return "redirect:/student/login"; // Redirect if not logged in
        }

        int examId = examSubmission.getExamId();
        Map<Integer, String> answers = examSubmission.getAnswers();

        System.out.println("Submitted Answers: " + answers);

        Exam exam = examService.getExamById(examId);
        if (exam == null) {
            return "errorPage"; // Handle invalid exam
        }

        List<Question> questionSet = questionService.getQuestionsByExamId(examId);
        int totalQuestions = questionSet.size();
        int score = 0;

        // Compare answers
        for (Question question : questionSet) {
            String submittedAnswer = answers.get(question.getQuestionId());

            System.out.println("QID: " + question.getQuestionId() + ", Correct: " + question.getAnswer() + ", Given: " + submittedAnswer);

            if (submittedAnswer != null && submittedAnswer.trim().equals(String.valueOf(question.getAnswer()).trim())) {
                score++;
            }
        }

        Result resultCheck = resultService.getPublishedDetail(examId);
        boolean pub = false;
        if (resultCheck != null)
            pub = resultCheck.getPublished();

        // Save result
        Result result = new Result(
                examId,
                exam.getExamName(),
                student.getStudentName(),
                student.getRollNo(),
                totalQuestions,
                score,
                pub
        );
        resultService.saveResult(result);

        model.addAttribute("message", "Exam Submitted Successfully!");
        return "examDone"; // Page to show result
    }


}

