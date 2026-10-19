<?php
require_once('database.php');
require('constants.php');

$statementSelectCourses = $database->prepare(
    'SELECT * FROM sk_courses ORDER BY courseID;'
);
$statementSelectCourses->execute();
$courses = $statementSelectCourses->fetchAll();
$statementSelectCourses->closeCursor();
?>
<!DOCTYPE html>
<html>

<head>
    <title>Course Manager</title>
    <link rel="stylesheet" type="text/css" href="main.css">
</head>

<body>
    <header>
        <h1>Course Manager</h1>
    </header>

    <main>
        <h1>Add Student</h1>
        <form action="add_student.php" method="post" id="add_student_form">
            <label>Course:</label>
            <select name="<?php echo FORM_COURSE_ID ?>">
                <?php foreach ($courses as $course): ?>
                    <option value="<?php echo $course['courseID']; ?>">
                        <?php echo $course['courseID'] . " - " . $course['courseName']; ?>
                    </option>
                <?php endforeach; ?>
            </select>
            <br />

            <label>First Name:</label>
            <input type="text" name="<?php echo FORM_STUDENT_FIRST_NAME ?>" required><br>

            <label>Last Name:</label>
            <input type="text" name="<?php echo FORM_STUDENT_LAST_NAME ?>" required><br>

            <label>Email:</label>
            <input type="email" name="<?php echo FORM_STUDENT_EMAIL ?>" required><br>


            <label>&nbsp;</label>
            <input type="submit" value="Add Student"><br>
        </form>
        <p><a href="index.php">View Student List</a></p>
    </main>

    <footer>
        <p>&copy;
            <?php echo date("Y"); ?> Suresh Kalathur.
        </p>
    </footer>
</body>

</html>