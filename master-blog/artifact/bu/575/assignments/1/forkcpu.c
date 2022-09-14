/* -------------------------------------------------------------------- */
/* PROGRAM: forkcpu.c                                                   */
/*                                                                      */
/* DESCRIPTION                                                          */
/*    This program illustrates the use of fork() and getpid() system    */
/* calls.  Note that write() is used instead of printf() since the      */
/* latter is buffered while the former is not.                          */
/* -------------------------------------------------------------------- */

#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>

/* This value is dependent on your CPU Performance. Why? */
#define MAX_COUNT  20

/* The buffer size for displaying updated message */
#define BUF_SIZE   100

int main()
{
    pid_t  pid;
    int    i;
    char   buf[BUF_SIZE];

    fork();
    pid = getpid();

    for (i = 1; i <= MAX_COUNT; i++)
    {
        sprintf(buf, "[My Name] This line is from pid %d, value = %d\n", pid, i);
        write(1, buf, strlen(buf));
    }

    return 0;
}