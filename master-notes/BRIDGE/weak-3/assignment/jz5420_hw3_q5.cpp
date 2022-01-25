/** 
 * Body mass index (BMI) is a number calculated from a person’s weight and height using the following formula:
 * weight/height^2. Where weight is in kilograms and height is in meters
 * 
 * According to the Centers for Disease Control and Prevention, the BMI is a fairly reliable indicator of body fatness for most people.
 * BMI does not measure body fat directly, but research has shown that BMI correlates to direct measures of body fat,
 * such as underwater weighing and dual-energy X-ray absorptiometry. 
 * 
 * The following table gives the weight status in respect to the BMI value:
 * | BMI Range    | Weight Status |
 * | ------------ | ------------- |
 * | Below 18.5   | Underweight   |
 * | [18.5, 25]   | Normal        |
 * | [25, 30]     | Overweight    |
 * | 30 and above | Obese         |
 * 
 * Write a program that prompts for weight (in pounds) and height (in inches) of a person,
 * and prints the weight status of that person.
 * 
 * Your program should interact with the user exactly as it shows in the following example:
    Please enter weight (in pounds): 135  
    Please enter height (in inches): 71  
    The weight status is: Normal
 * Note: 1 pound is 0.453592 kilograms and 1 inch is 0.0254 meters.
*/
#include <iostream>
#include <string>
using namespace std;

const float ONE_POUND_TO_KILOGRAM = 0.453592, ONE_INCH_TO_METER = 0.0254;
const string STATUS_UNDERWEIGHT = "Underweight", STATUS_NORMAL = "Normal",
             STATUS_OVERWEIGHT = "Overweight", STATUS_OBESE = "Obese";
int main()
{
    float weight_pound, height_inch;
    cout << "Please enter weight (in pounds): ";
    cin >> weight_pound;
    float weight_kilogram = weight_pound * ONE_POUND_TO_KILOGRAM;
    cout << "Please enter height (in inches): ";
    cin >> height_inch;
    float height_meter = height_inch * ONE_INCH_TO_METER;

    float body_mess_index = weight_kilogram / (height_meter * height_meter);
    string status;
    if (body_mess_index < 18.5)
    {
        status = STATUS_UNDERWEIGHT;
    }
    else if (body_mess_index >= 18.5 && body_mess_index < 25)
    {
        status = STATUS_NORMAL;
    }
    else if (body_mess_index >= 25 && body_mess_index < 30)
    {
        status = STATUS_OVERWEIGHT;
    }
    else
    {
        status = STATUS_OBESE;
    }

    cout << "The weight status is: " << status << endl;
    return 0;
}