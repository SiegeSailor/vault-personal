<?php
require_once('constants.php');

$format = filter_input(INPUT_GET, QUERY_FORMAT);
$action = filter_input(INPUT_GET, QUERY_ACTION);
$course = filter_input(INPUT_GET, QUERY_COURSE);

if ($format != FORMAT_JSON) {
    exit("Unsupported format.");
}

require_once('database.php');

switch ($action) {
    case ACTION_COURSES:
        $statementSelectCourses = $database->prepare(
            'SELECT * FROM sk_courses ORDER BY courseID;'
        );
        $statementSelectCourses->execute();
        $courses = $statementSelectCourses->fetchAll(PDO::FETCH_ASSOC);
        $statementSelectCourses->closeCursor();
        if (count($courses)) {
            echo json_encode($courses);
        } else {
            echo 'No result.';
        }
        break;
    case ACTION_STUDENTS:
        if (empty($course)) {
            echo 'Must specify course.';
        } else {
            $statementSelectStudents = $database->prepare(
                'SELECT * FROM sk_students WHERE courseID = :course ORDER BY studentID;'
            );
            $statementSelectStudents->bindValue(':course', $course);
            $statementSelectStudents->execute();
            $students = $statementSelectStudents->fetchAll(PDO::FETCH_ASSOC);
            $statementSelectStudents->closeCursor();
            if (count($students)) {
                echo json_encode($students);
            } else {
                echo 'No result.';
            }
        }
        break;
    default:
        exit("Unsupported action.");
}
?>