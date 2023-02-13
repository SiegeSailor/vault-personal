<?php

function incomeTaxSingle($taxableIncome) {
    $incTax = 0.0;
    return $incTax;
}

function incomeTaxMarriedJointly($taxableIncome) {
    $incTax = 0.0;
    return $incTax;
}

function incomeTaxMarriedSeparately($taxableIncome) {
    $incTax = 0.0;
    return $incTax;
}

function incomeTaxHeadOfHousehold($taxableIncome) {
    $incTax = 0.0;
    return $incTax;
}

?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HW4 Part1 - Zhang</title>

  <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">

  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">

    <h3>Income Tax Calculator</h3>

    <form class="form-horizontal" method="post">

        
        <div class="form-group">
            <label class="control-label col-sm-2" for="netIncome">Your Net Income:</label>
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

        // Fill in the rest of the PHP code for form submission results

        if(isset($_POST['netIncome'])) {

            echo "Results...";



        }

    ?>

</div>

</body>
</html>