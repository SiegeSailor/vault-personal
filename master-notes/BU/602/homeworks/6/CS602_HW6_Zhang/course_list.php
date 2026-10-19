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
    <link rel="stylesheet" type="text/css" href="main.css" />
</head>

<body>
    <header>
        <h1>Course Manager</h1>
    </header>
    <main>
        <h1>Course List</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
            </tr>
            <?php foreach ($courses as $course): ?>
                <tr>
                    <td>
                        <?php echo $course['courseID']; ?>
                    </td>
                    <td>
                        <?php echo $course['courseName']; ?>
                    </td>
                </tr>
            <?php endforeach; ?>
        </table>
        <p>
        <h2>Add Course</h2>

        <form action="add_course.php" method="post" id="add_course_form">
            <label>Course Id:</label>
            <input type="text" name="<?php echo FORM_COURSE_ID ?>" required><br>
            <label>Course Name:</label>
            <input type="text" name="<?php echo FORM_COURSE_NAME ?>" width="200" required><br>
            <label>&nbsp;</label>
            <input type="submit" value="Add Course"><br>
        </form>

        <br>
        <p><a href="index.php">List Students</a></p>

    </main>

    <footer>
        <p>&copy;
            <?php echo date("Y"); ?> Suresh Kalathur
        </p>
    </footer>
</body>

</html>