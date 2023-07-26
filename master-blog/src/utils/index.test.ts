import index from ".";

describe("format", function () {
  describe("Converting pathname to readable title.", function () {
    test.each([
      ["", "Home"],
      ["logs-viewer", "Logs Viewer"],
      ["list", "List"],
    ])("Format %p to %p", function (pathname, readable) {
      expect(index.format.title(pathname)).toEqual(readable);
    });
  });

  const DATE_TIME_REGEX = /^(\d{2}\/){2}\d{4} (\d{2}:){2}\d{2}$/;
  describe("Formatting timestamp to date and time.", function () {
    test.each([[1688409819021], [1688043339275]])(
      "Format %p to %p.",
      function (timestamp) {
        expect(
          DATE_TIME_REGEX.test(index.format.dateTime(new Date(timestamp)))
        ).toBeTruthy();
      }
    );
  });

  const DATE_REGEX = /^(\d{2}\/){2}\d{4}$/;
  describe("Formatting timestamp to date.", function () {
    test.each([[1688409819021], [1688043339275]])(
      "Format %p to %p.",
      function (timestamp) {
        expect(
          DATE_REGEX.test(index.format.date(new Date(timestamp)))
        ).toBeTruthy();
      }
    );
  });
});
