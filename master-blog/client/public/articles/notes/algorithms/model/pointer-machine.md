Another abstract model is named a “Pointer Machine”, which is a
higher-level programming abstraction
»In the RAM model, we do not have many concepts that we would need
to have for a high-level programming abstraction
»E.g., in dynamic memory allocation, we would need other ways to run
computations on the data than working always with arrays
»We can have dynamically allocated objects so that an object has a
constant number of fields. A field then can be a word (e.g., a float or an
integer, or a pointer)

# working with pointer machine

▪ A pointer is a useful abstract “thing” that points to another
object
▪ Can be used to point to another object or memory region that
holds that object
»A pointer points to an object or it has a “Null” value
»A pointer sometimes has a value to point to or has a default initialization
value such as “Null”
For example, the RAM model can have an index storing the position of each
word, you might need to have a pointer that points to different index numbers
that you can access by using a pointer
