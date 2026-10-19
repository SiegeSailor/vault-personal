<?php
define(
  'TAX_RATES',
  array(
    'Single' => array(
      'Rates' => array(10, 12, 22, 24, 32, 35, 37),
      'Ranges' => array(0, 9700, 39475, 84200, 160725, 204100, 510300),
      'MinTax' => array(0, 970, 4543, 14382, 32748, 46628, 153798)
    ),
    'Married_Jointly' => array(
      'Rates' => array(10, 12, 22, 24, 32, 35, 37),
      'Ranges' => array(0, 19400, 78950, 168400, 321450, 408200, 612350),
      'MinTax' => array(0, 1940, 9086, 28765, 65497, 93257, 164709)
    ),
    'Married_Separately' => array(
      'Rates' => array(10, 12, 22, 24, 32, 35, 37),
      'Ranges' => array(0, 9700, 39475, 84200, 160725, 204100, 306175),
      'MinTax' => array(0, 970, 4543, 14382.50, 32748.50, 46628.50, 82354.75)
    ),
    'Head_Household' => array(
      'Rates' => array(10, 12, 22, 24, 32, 35, 37),
      'Ranges' => array(0, 13850, 52850, 84200, 160700, 204100, 510300),
      'MinTax' => array(0, 1385, 6065, 12962, 31322, 45210, 152380)
    )
  )
);

function incomeTax($taxableIncome, $status)
{
  $incTax = 0.0;
  $index = 0;
  for ($count = 0; $count < 7; $count += 1) {
    $range = TAX_RATES[$status]["Ranges"][$count];
    if ($range > $taxableIncome) {
      $index = $count - 1;
      break;
    }
  }

  $rate = TAX_RATES[$status]["Rates"][$index];
  $range = TAX_RATES[$status]["Ranges"][$index];
  $minTax = TAX_RATES[$status]["MinTax"][$index];
  $incTax = $minTax + ($taxableIncome - $range) * ($rate / 100);
  return $incTax;
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>HW4 Part2 - Zhang</title>
  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>

<body>
  <div class="container">
    <h3>Income Tax Calculator</h3>

    <form class="form-horizontal" method="post">
      <div class="form-group">
        <label class="control-label col-sm-2">Enter Net Income:</label>
        <div class="col-sm-10">
          <input type="number" step="any" name="netIncome" placeholder="Taxable  Income" required autofocus>
        </div>
      </div>
      <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </div>

    </form>

    <?php
    if (isset($_POST['netIncome'])) {
      $income = htmlspecialchars($_POST["netIncome"]);
      $incomeString = "$" . number_format($income, 2);
      $singleTax = "$" . number_format(incomeTax($income, 'Single'), 2);
      $jointlyTax = "$" . number_format(incomeTax($income, 'Married_Jointly'), 2);
      $separatelyTax = "$" . number_format(incomeTax($income, 'Married_Separately'), 2);
      $householdTax = "$" . number_format(incomeTax($income, 'Head_Household'), 2);

      echo "<p style='font-size: 16px; font-weight: bold;'>With a net textable income of " . $incomeString . "</p>";
      echo '
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th scope="col">Status</th>
                        <th scope="col">Tax</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Single</td>
                        <td>' . $singleTax . '</td>
                    </tr>
                    <tr>
                        <td>Married Filing Jointly</td>
                        <td>' . $jointlyTax . '</td>
                    </tr>
                    <tr>
                        <td>Married Filing Separately</td>
                        <td>' . $separatelyTax . '</td>
                    </tr>
                    <tr>
                        <td>Head of Household</td>
                        <td>' . $householdTax . '</td>
                    </tr>
                </tbody>
            </table>
          ';
    }
    ?>

    <h3>2019 Tax Tables</h3>
    <?php
    foreach (TAX_RATES as $title => $subArray) {
      echo "<p style='font-size: 16px; font-weight: bold; padding: 0; margin: 0;'>$title</p>";
      echo '
          <table class="table table-striped">
            <thead>
              <tr>
                  <th scope="col">Taxable Income</th>
                  <th scope="col">Tax Rate</th>
              </tr>
            </thead>
            <tbody>
                ';

      for ($count = 0; $count < 7; $count += 1) {
        $rate = $subArray['Rates'][$count] . "%";
        $range = $subArray['Ranges'][$count];
        $rangeText = "$" . number_format($subArray['Ranges'][$count]);
        $rangePlusOneText = "$" . number_format($range + 1);
        $minTax = "$" . number_format($subArray['MinTax'][$count], 2);
        $taxRate = $count === 0 ? $rate : $minTax . " plus " . $rate . " of the amount over " . $rangeText;
        $income = "";
        $nextRange = $count !== 6 ? "$" . number_format($subArray['Ranges'][$count + 1]) : " or more";
        if ($count === 0 || $count === 6) {
          $income = $count === 0 ? $rangeText . " - " . $nextRange : $rangePlusOneText . $nextRange;
        } else {
          $income = $rangePlusOneText . " - " . $nextRange;
        }
        echo '
                <tr>
                    <td>' . $income . '</td>
                    <td>' . $taxRate . '</td>
                </tr>
                    ';
      }

      echo '</tbody></table><br/>';
    }
    ?>
  </div>

</body>

</html>