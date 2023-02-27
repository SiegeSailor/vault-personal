<?php
require_once('database.php');
require('constants.php');

$statementSelectCourses = $database->prepare(
    'SELECT * FROM sk_courses ORDER BY courseID;'
);
$statementSelectCourses->execute();
$courses = $statementSelectCourses->fetchAll();
$statementSelectCourses->closeCursor();

if (!isset($currentCourseID)) {
    $currentCourseID = filter_input(
        INPUT_GET,
        QUERY_CURRENT_COURSE_ID,
    );
    if ($currentCourseID == null || $currentCourseID == false) {
        $currentCourseID = $courses[0]['courseID'];
    }
}

foreach ($courses as $course) {
    if ($course['courseID'] == $currentCourseID) {
        $currentCourse = $course;
        break;
    }
}
if (!isset($currentCourse)) {
    $error = 'There is no matched course with the current course ID.';
    include('error.php');
    exit();
}

$statementSelectStudents = $database->prepare(
    'SELECT * FROM sk_students WHERE courseID = :current_course_id ORDER BY studentID;'
);
$statementSelectStudents->bindValue(':current_course_id', $currentCourseID);
$statementSelectStudents->execute();
$students = $statementSelectStudents->fetchAll();
$statementSelectStudents->closeCursor();
?>


<!DOCTYPE html>
<html>

<head>
    <title>Course Manager</title>
    <link rel="stylesheet" type="text/css" href="main.css" />
</head>

<body>
    <header>
        <h1>Course Manager</h1>
    </header>
    <main>
        <center>
            <h1>Student List</h1>
        </center>

        <aside>
            <h2>Courses</h2>
            <nav>
                <ul>
                    <?php foreach ($courses as $course): ?>
                        <li><a href="<?php echo '?' . QUERY_CURRENT_COURSE_ID . '=' . $course['courseID']; ?>">
                                <?php echo $course['courseID']; ?>
                            </a>
                        </li>
                    <?php endforeach; ?>
                </ul>
            </nav>
        </aside>

        <section>
            <h2>
                <?php
                echo $currentCourseID . ' - ' . $currentCourse['courseName'];
                ?>
            </h2>
            <table>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>&nbsp;</th>
                </tr>
                <?php foreach ($students as $student): ?>
                    <tr>
                        <td>
                            <?php echo $student['firstName']; ?>
                        </td>
                        <td>
                            <?php echo $student['lastName']; ?>
                        </td>
                        <td>
                            <?php echo $student['email']; ?>
                        </td>
                        <td>
                            <form action="delete_student.php" method="post">
                                <input type="hidden" name="<?php echo FORM_STUDENT_ID ?>"
                                    value="<?php echo $student['studentID']; ?>">
                                <input type="hidden" name="<?php echo FORM_COURSE_ID ?>"
                                    value="<?php echo $student['courseID']; ?>">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                <?php endforeach; ?>
            </table>

            <p><a href="add_student_form.php">Add Student</a></p>
            <p><a href="course_list.php">List Courses</a></p>
        </section>
    </main>

    <footer>
        <p>&copy;
            <?php echo date("Y"); ?> Suresh Kalathur
        </p>
    </footer>
</body>

</html>