<?php
require_once('database.php');
require('constants.php');

$courseID = filter_input(INPUT_POST, FORM_COURSE_ID);
$courseName = filter_input(INPUT_POST, FORM_COURSE_NAME);
if (empty($courseID) || empty($courseName)) {
    $error = 'Invalid course creation form data.';
    include('error.php');
    exit();
}

$statementSelectCourse = $database->prepare(
    'SELECT * FROM sk_courses WHERE courseID = :course_id OR courseName = :course_name;'
);
$statementSelectCourse->bindValue(':course_id', $courseID);
$statementSelectCourse->bindValue(':course_name', $courseName);
$statementSelectCourse->execute();
$course = $statementSelectCourse->fetch();
$statementSelectCourse->closeCursor();
$isCourseExist = $course != false;
if ($isCourseExist) {
    $error = 'There is a course exist with the same id or name.';
    include('error.php');
    exit();
} else {
    $statementInsertCourse = $database->prepare(
        'INSERT INTO sk_courses (courseID, courseName) VALUES (:course_id, :course_name);'
    );
    $statementInsertCourse->bindValue(':course_id', $courseID);
    $statementInsertCourse->bindValue(':course_name', $courseName);
    $statementInsertCourse->execute();
    $statementInsertCourse->closeCursor();
    include('course_list.php');
}
?>