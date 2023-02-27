const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const instructorSchema = new Schema({
    instructorName: String,
    instructorCourses: [
            {
                type: mongoose.Schema.Types.ObjectId,
                ref: "Course"
            }
    ]
}, {collection : 'instructors_sk_mm'});

const Instructor = mongoose.model("Instructor", instructorSchema);

module.exports = Instructor;