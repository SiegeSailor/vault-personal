const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const courseSchema = new Schema({
    courseNumber: String,
    courseName: String,
    courseInstructors: [
            {
                type: mongoose.Schema.Types.ObjectId,
                ref: "Instructor"
            }
    ],
    courseStudents: [
        {
            type: mongoose.Schema.Types.ObjectId,
            ref: "Student"
        }
    ]   
}, {collection : 'courses_sk_mm'});

const Course = mongoose.model("Course", courseSchema);

module.exports = Course;