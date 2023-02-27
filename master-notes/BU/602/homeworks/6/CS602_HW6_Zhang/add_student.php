<?php
require_once('database.php');
require('constants.php');

$courseID = filter_input(INPUT_POST, FORM_COURSE_ID);
$studentFirstName = filter_input(INPUT_POST, FORM_STUDENT_FIRST_NAME);
$studentLastName = filter_input(INPUT_POST, FORM_STUDENT_LAST_NAME);
$studentEmail = filter_input(INPUT_POST, FORM_STUDENT_EMAIL);
if (
    empty($courseID) || empty($studentFirstName ||
    empty($studentLastName) || empty($studentEmail))
) {
    $error = 'Invalid student creation form data.';
    include('error.php');
    exit();
}

$statementSelectStudent = $database->prepare(
    'SELECT * FROM sk_students WHERE email = :student_email;'
);
$statementSelectStudent->bindValue(':student_email', $studentEmail);
$statementSelectStudent->execute();
$student = $statementSelectStudent->fetch();
$statementSelectStudent->closeCursor();
$isCourseExist = $student != false;
if ($isCourseExist) {
    $error = 'There is a student exist with the same information (maybe in other courses).';
    include('error.php');
    exit();
} else {
    $statementInsertCourse = $database->prepare(
        'INSERT INTO sk_students (courseID, firstName, lastName, email) VALUES (:course_id, :student_first_name, :student_last_name, :student_email);'
    );
    $statementInsertCourse->bindValue(':course_id', $courseID);
    $statementInsertCourse->bindValue(':student_first_name', $studentFirstName);
    $statementInsertCourse->bindValue(':student_last_name', $studentLastName);
    $statementInsertCourse->bindValue(':student_email', $studentEmail);
    $statementInsertCourse->execute();
    $statementInsertCourse->closeCursor();
    header('Location: index.php?' . QUERY_CURRENT_COURSE_ID . '=' . $courseID);
}
?>