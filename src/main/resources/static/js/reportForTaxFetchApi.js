//ID
const REPORT_SHOW = "report-show";
const REPORT_CREATE = "report-create";
const YEAR = "-year";
const QUARTER = "-quarter";
const VALUE = "-value";
const DATA_REPORTS = "data-reports";

//URl
const URL_TAX = "/tax";
const URL_TAX_CREATE = "/tax/create";

function getRowReport(tmp, report) {
    tmp += "<tr><td>" + report.year + "</td>";
    tmp += "<td>" + report.quarter + "</td>";
    tmp += "<td>" + Number(report.income).toFixed(2) + "</td>";
    tmp += "<td>" + report.tax + "</td>";
    tmp += "<td>" + Number(report.sumForTax).toFixed(2) + "</td>";
    tmp += "<td>" + Number(report.clearIncome).toFixed(2) + "</td>";
    return tmp;
}

function showReportForTax() {
    let year = document.getElementById(REPORT_SHOW + YEAR);
    let quarter = document.getElementById(REPORT_SHOW + QUARTER);
    let table = document.getElementById(DATA_REPORTS);
    let tmp = EMPTY_VALUE;

    if (checkQuarter(quarter)) {
        fetch(URL_TAX + "/search" + `?year=${year.value}&quarter=${quarter.value}`)
            .then((resp) => resp.json())
            .then(function (data) {
                year.value = EMPTY_VALUE;
                quarter.value = EMPTY_VALUE;
                console.log(data);
                table.innerHTML = EMPTY_VALUE;
                if (data.length > 0) {
                    data.forEach((report => {
                        tmp = getRowReport(tmp, report);
                    }))
                }
                table.innerHTML = tmp;
            })
            .catch(function (e) {
                console.log(e);
            })
    }
}

function createReportForTax() {
    let year = document.getElementById(REPORT_CREATE + YEAR);
    let quarter = document.getElementById(REPORT_CREATE + QUARTER);
    let tax = document.getElementById(REPORT_CREATE + VALUE);
    let table = document.getElementById(DATA_REPORTS);
    let tmp = EMPTY_VALUE;

    if (checkQuarter(quarter)) {
        if (year.value != "" && quarter.value != "" && tax.value != "") {
            fetchSendData(URL_TAX_CREATE, {
                year: year.value,
                quarter: quarter.value,
                tax: tax.value
            }, POST)
                .then((data) => {
                    year.value = EMPTY_VALUE;
                    quarter.value = EMPTY_VALUE;
                    tax.value = EMPTY_VALUE;

                    console.log(data);
                    document.getElementById(DATA_REPORTS).innerHTML = EMPTY_VALUE;

                    tmp = getRowReport(tmp, data);

                    table.innerHTML = tmp;
                });
        }
    }
}

function checkQuarter(quarter) {
    if (quarter.value != "") {
        return (quarter.value > 0 && quarter.value <= 4);
    }
    return true;
}