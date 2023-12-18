
        google.charts.load('current', { packages: ['corechart', 'bar'] });
        google.charts.setOnLoadCallback(drawChart);

        // Bind click event to make an ajax call to a POST request to get data from the server.
        $("#btnGetChartData").click(function () {
            $("#btnGetChartData").hide();
            $.ajax({
                url: "Inventory",
                type: "POST",
                data: {},
                success: function (msg) {
                    console.log("Received data: " + msg);
                    createDataTable(msg);
                },
                error: function () {
                    console.log("Error occurred while making the ajax call.");
                }
            });
        });

        // This method will parse JSON data and build a data table required by Google Charts.
        function createDataTable(jsonData) {
            var parsedData = $.parseJSON(jsonData);
            var data = new Array();
            var productNameArr = [];

            // Create an array of product names
            for (var i = 0; i < parsedData.length; i++) {
                var productName = parsedData[i]["name"];
                if (!productNameArr.includes(productName)) {
                    productNameArr.push(productName);
                }
            }

            // Create header array for Google Charts
            var headingArray = ["Product", "Count"];
            data[0] = headingArray;

            // Create data array to plot the chart
            for (var i = 0; i < productNameArr.length; i++) {
                var productName = productNameArr[i];
                var count = 0;

                for (var j = 0; j < parsedData.length; j++) {
                    if (parsedData[j]["name"] === productName) {
                        count += parseInt(parsedData[j]["count"]);
                    }
                }

                data[i + 1] = [productName, count];
            }

            drawChart(data);
        }

        // Plot the chart using the 2D array
        function drawChart(data) {
            var options = {
                width: 600,
                height: 1200,
                chart: {
                    title: 'Trending Products Chart',
                },
                hAxis: {
                    title: 'Total no of Items Available', // Set the label for the X-axis
                },
                vAxis: {
                    title: 'Product Name',
                maxTextLines:1,
                },
                bars: 'vertical',
            };

            var chartData = google.visualization.arrayToDataTable(data);
            var chart = new google.visualization.BarChart(document.getElementById('chart_div'));
            chart.draw(chartData, options);
        }
    