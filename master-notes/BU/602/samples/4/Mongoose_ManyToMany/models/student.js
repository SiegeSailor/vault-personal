const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const studentSchema = new Schema({
    studentName: String,
    studentDegree: String,
    studentCourses: [
            {
                type: mongoose.Schema.Types.ObjectId,
                ref: "Course"
            }
    ]
}, {collection : 'students_sk_mm'});

const Student = mongoose.model("Student", studentSchema);

module.exports = Student;
