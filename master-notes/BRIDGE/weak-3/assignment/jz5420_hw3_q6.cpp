/**
 * Write a program that computes the cost of a long-distance call. The cost of the call is 
 * determined according to the following rate schedule: 
    • Any call started between 8:00 A.M. and 6:00 P.M., Monday through Friday, is billed at a 
    rate of $0.40 per minute. 
    • Any call starting before 8:00 A.M. or after 6:00 P.M., Monday through Friday, is charged 
    at a rate of $0.25 per minute. 
    • Any call started on a Saturday or Sunday is charged at a rate of $0.15 per minute. 
 * The input will consist of the day of the week, the time the call started, and the length of the 
    call in minutes. 
 * The output will be the cost of the call.
 *
 * Notes:
    1. The time is to be input in 24-hour notation, so the time 1:30 P.M. is input as 13:30 
    2. The day of the week will be read as one of the following two character string:
        Mo Tu We Th Fr Sa Su
    3. The number of minutes will be input as a positive integer. 
 */
#include <iostream>
#include <string>
using namespace std;

const float COST_RATE_WORKTIME = 0.40, COST_RATE_BEFORE_AFTER_WORK = 0.25, COST_RATE_HOLIDAY = 0.15;
const int CALL_WORKTIME_HOUR_START = 8, CALL_WORKTIME_HOUR_END = 18;
const string MONDAY = "Mo", THUSDAY = "Tu", WENSDAY = "We",
             THURSDAY = "Th", FRIDAY = "Fr", SATURDAY = "Sa", SUNDAY = "Su";
int main()
{
   cout.setf(ios::fixed);
   cout.setf(ios::showpoint);
   cout.precision(2);

   int input_hour, input_minute, input_call_length_minute;
   char input_notation_time;
   bool is_weakday, is_holiday;
   string input_weakday;

   cout << "Enter the time the call started in 24-hour format such as 13:30: ";
   cin >> input_hour >> input_notation_time >> input_minute;
   if (input_hour < 0 || input_hour > 24 || input_minute < 0 || input_minute > 60)
   {
      cout << "Invalid 24-hour format time input. Please try again." << endl;
      main();
      return 1;
   }

   cout << "Enter the weakday abbreviation the call started such as Mo (Monday), Tu (Thusday), or Su (Sunday): ";
   cin >> input_weakday;
   is_weakday = input_weakday == MONDAY || input_weakday == THUSDAY || input_weakday == WENSDAY ||
                input_weakday == THURSDAY || input_weakday == FRIDAY;
   is_holiday = input_weakday == SATURDAY || input_weakday == SUNDAY;
   if (!is_weakday && !is_holiday)
   {
      cout << "Invalid weakday input. Please try again." << endl;
      main();
      return 1;
   }

   cout << "Enter the length of the call in minutes: ";
   cin >> input_call_length_minute;
   if (input_call_length_minute < 0)
   {
      cout << "Invalid call length input. Please try again." << endl;
      main();
      return 1;
   }

   float output_cost;
   bool is_before_after_worktime = input_hour < CALL_WORKTIME_HOUR_START || input_hour >= CALL_WORKTIME_HOUR_END;
   if (is_weakday)
   {
      if (is_before_after_worktime)
      {
         cout << "Your cost per minute is $" << COST_RATE_BEFORE_AFTER_WORK
              << " since the call starting before 8:00 A.M. or after 6:00 P.M., Monday through Friday." << endl;
         output_cost = input_call_length_minute * COST_RATE_BEFORE_AFTER_WORK;
      }
      else
      {
         cout << "Your cost per minute is $" << COST_RATE_WORKTIME
              << " since the call started between 8:00 A.M. and 6:00 P.M., Monday through Friday." << endl;
         output_cost = input_call_length_minute * COST_RATE_WORKTIME;
      }
   }
   else if (is_holiday)
   {
      cout << "Your cost per minute is $" << COST_RATE_HOLIDAY
           << " since the call started on a Saturday or Sunday." << endl;
      output_cost = input_call_length_minute * COST_RATE_HOLIDAY;
   }

   cout << "Your call cost: $" << output_cost << endl;

   return 0;
}