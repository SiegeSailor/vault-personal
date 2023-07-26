const format = {
  title: function (path?: string) {
    return path
      ? path
          .split("-")
          .map((word) => `${word[0].toUpperCase()}${word.slice(1)}`)
          .join(" ")
      : "Home";
  },
  dateTime: function (date: Date) {
    return `${(date.getMonth() + 1).toString().padStart(2, "0")}/${date
      .getDate()
      .toString()
      .padStart(2, "0")}/${date.getFullYear()} ${date
      .getHours()
      .toString()
      .padStart(2, "0")}:${date.getMinutes().toString().padStart(2, "0")}:${date
      .getSeconds()
      .toString()
      .padStart(2, "0")}`;
  },
  date: function (date: Date) {
    return this.dateTime(date).slice(0, 10);
  },
  time: function (date: Date) {
    return this.dateTime(date).slice(11);
  },
};

const date = {
  monthDays: function (date: Date) {
    return new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
  },
  yearDays: function (date: Date) {
    let total = 0;
    for (let month = 0; month < date.getMonth(); month++)
      total += new Date(date.getFullYear(), month + 1, 0).getDate();
    return total;
  },
};

export default { format, date };
