/** Problem 1 */
const data = [
  {
    courseName: "NodeJs",
    courseId: "cs1",
    students: [
      { id: "s1", name: "John Doe", grades: [73, 85, 69] },
      { id: "s2", name: "Jane Smith", grades: [75, 76, 70] },
    ],
  },
  {
    courseName: "PHP",
    courseId: "cs2",
    students: [
      { id: "s1", name: "John Doe", grades: [88, 61, 68] },
      { id: "s3", name: "Mary Jones", grades: [75, 66, 91] },
      { id: "s4", name: "Bill Evans", grades: [89, 91, 84] },
    ],
  },
];

function lookupByStudentId(id) {
  const result = { id, name: "", courses: [] };
  for (const { courseName, courseId, students } of data) {
    for (const student of students) {
      if (student.id === id) {
        result.name = student.name;
        result.courses.push({ courseName, courseId, grades: student.grades });
        break;
      }
    }
  }

  return result;
}

function getUniqueStudents() {
  const result = [];
  const map = {};
  for (const { students } of data) {
    for (const { name, id } of students) {
      if (!map[id]) {
        result.push({ name, id });
        map[id] = true;
      }
    }
  }

  return result;
}

module.exports = {
  lookupByStudentId,
  getUniqueStudents,
  data,
};
