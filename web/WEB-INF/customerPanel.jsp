<%-- 
    Document   : customerPanel
    Created on : 11-Nov-2019, 15:35:18
    Author     : jordandraper
--%>

<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <title>West England Drivers Association</title>
        <link href="css/fixed-two-column.css" rel="stylesheet">
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <jsp:include page="/WEB-INF/navigationBar.jsp"/> 
            </div>
            <div id="main">
                <div id="menu">
                    <jsp:include page="/WEB-INF/customerSidePanel.jsp"/> 
                </div>
                <div id="content">
                    <h2>Welcome to your profile page</h2>
                    <fieldset>
                        <form method="POST" action="AdminService.do">           
                            <input name="tbl" type="submit" id="signup" class="form-submit" value="Update profile details"/>  
                            <div></div>
                            Email: <input type="text" name="username" value="<%=(String) (request.getAttribute("username"))%>">
                            Full name: <input type="text" name="fullname" value="<%=(String) (request.getAttribute("fullname"))%>">
                            Date of Birth: <input type="text" name="dateofbirth" value="<%=(String) (request.getAttribute("dateofbirth"))%>">
                            Address: <input type="text" name="address" id="address" value="<%=(String) (request.getAttribute("address"))%>"readonly>  

                        </form>
                        <form id="example_form">
                            <div class="address_container">
                                <div class="form-group">
                                    <label for="address"></label>
                                    <input
                                        type="text"
                                        class="form-control"
                                        id="address"
                                        placeholder="Search for your address here! e.g. Manor Farm Barns, Framingham Pigot or NR14 7PZ"
                                        aria-owns="address_result"
                                        autocomplete="yes"
                                        autocorrect="off"
                                        />
                                </div>
                                <button type="submit" class="btn btn-default" id="search">
                                    Search
                                </button>
                                <div
                                    id="address_status"
                                    aria-live="assertive"
                                    aria-atomic="true"
                                    role="status"
                                    class="sr-only"
                                    ></div>
                                <div id="address_results"></div>
                            </div>

                        </form>



                    </fieldset>
                    <fieldset>
                        Date of Registration: <input type="text" name="registrationDate" value="<%=(String) (request.getAttribute("dateofregistration"))%>"readonly>
                        Profile Type: <input type="text" name="profileType" value="<%=(String) (request.getAttribute("profiletype"))%>"readonly>
                        Balance: <input type="text" name="accountBalance" value="<%=(String) (request.getAttribute("balance"))%>"readonly>
                    </fieldset>
                </div>
                <div class="clearer"></div>
            </div>
        </div>



        <script>
            // This script will present address results in a <select> element.
            var api_key = "PCWMJ-DKYY3-7SV57-TGWS2";
            var example_form = document.getElementById("example_form");
            example_form.addEventListener("submit", function (e) {
                e.preventDefault();
                // The field we will use for search term input
                var address = document.getElementById("address");
                // Where we will put our search results
                var address_results = document.getElementById("address_results");
                // Accessible status messages for assistive technologies like screen readers
                var address_status = document.getElementById("address_status");
                address_search(address, address_results, address_status);
            });
            function address_search(
                    input_element,
                    address_results,
                    status_element,
                    page
                    ) {
                var page = page || 0;
                var address = input_element.value.trim();
                if (address != "") {
                    // Remove any previous validation results
                    if (page == 0)
                        address_results.innerHTML = "";
                    // Create a loading message
                    var loading_html = document.createElement("div");
                    loading_html.setAttribute("id", "address_loading");
                    loading_html.textContent = "Searching addresses...";
                    address_results.insertAdjacentElement("afterend", loading_html);
                    status_element.textContent = "Searching addresses";
                    // Country hard coded to GB for this example
                    var country_code = "GB";
                    // Create the URL to API including API key and encoded search term
                    var address_url =
                            "https://ws.postcoder.com/pcw/" +
                            api_key +
                            "/address/" +
                            country_code +
                            "/" +
                            encodeURIComponent(address) +
                            "?lines=2&page=" +
                            page;
                    // Call the API
                    var address_request = new XMLHttpRequest();
                    address_request.open("GET", address_url, true);
                    address_request.onload = function () {
                        if (address_request.status >= 200 && address_request.status < 400) {
                            loading_html.remove();
                            address_results.innerHTML = "";
                            var data = JSON.parse(address_request.responseText);
                            // For only one result, simply populate the fields, rather than asking the user to select from list
                            if (data.length == 1) {
                                select_address(data[0], address_results, status_element);
                                status_element.textContent =
                                        '"' +
                                        data[0].summaryline +
                                        '" selected, address fields below populated';
                            } else if (data.length > 1) {
                                // Produce the <select> element and a label
                                var select_label = document.createElement("label");
                                var select_element = document.createElement("select");
                                select_element.id = "addressSelect";
                                select_element.className = "form-control form-control-lg";
                                select_label.htmlFor = "addressSelect";
                                select_label.textContent = "";
                                // Give the select element a listener function that is passed the address data for that option
                                select_element.addEventListener(
                                        "change",
                                        (function (item) {
                                            return function (event) {
                                                if (event.target.value === "morePlease") {
                                                    // Here we handle a request for more addresses
                                                    // (if more than 100 were returned from the search)
                                                    // More details on that later
                                                    address_search(
                                                            input_element,
                                                            address_results,
                                                            status_element,
                                                            data[data.length - 1].nextpage
                                                            );
                                                } else {
                                                    select_address(
                                                            data[event.target.value],
                                                            address_results,
                                                            status_element
                                                            );
                                                }
                                            };
                                        })(data)
                                        );
                                address_results.appendChild(select_label);
                                address_results.appendChild(select_element);
                                // Fill it with <option>s
                                for (var i = 0; i < data.length; i++) {
                                    // Add a placeholder if first option
                                    if (i === 0) {
                                        var address_option = new Option(
                                                "Select an address...",
                                                null
                                                );
                                        select_element.appendChild(address_option);
                                    }
                                    // Text for each option is the summaryline, the value is just the index of the loop
                                    var address_option = new Option(data[i].summaryline, i);
                                    select_element.appendChild(address_option);
                                }
                                // Check if we have more than one page of results (Slight edge case)
                                // Either let your user page through the results using a button or
                                // show a message to encourage them to refine their search.
                                // Typically adding a house number or name along with postcode helps
                                var last_index = data.length - 1;
                                if (data[last_index].morevalues) {
                                    // Create another select option and add some context to text, using totalresults element
                                    var show_more_option = new Option(
                                            data[last_index].totalresults +
                                            " addresses found, click to show next 100",
                                            "morePlease"
                                            );
                                    select_element.appendChild(show_more_option);
                                } else {
                                    status_element.textContent = data.length + " addresses found";
                                }
                            } else {
                                address_results.textContent = "No addresses found";
                                status_element.textContent = "No addresses found";
                            }
                        } else {
                            loading_html.remove();
                            status_element.textContent = "Error occurred";
                            address_results.textContent = "Error occurred";
                            // Triggered if API does not return HTTP code between 200 and 399
                            // More info - https://postcoder.com/docs/error-handling
                        }
                    };
                    address_request.onerror = function () {
                        loading_html.remove();
                        status_element.textContent = "Error occurred";
                        address_results.textContent = "Error occurred";
                        // Triggered if API cannot be reached
                        // More info - https://postcoder.com/docs/error-handling
                    };
                    address_request.send();
                } else {
                    // Could show an "Address search term is required" message here
                }
            }
            function select_address(address, address_results, status_element) {
                status_element.textContent =
                        '"' +
                        address.summaryline +
                        '" selected, address fields below populated';
                // Populate fields
                //document.getElementById("address_line_1").value =
                address.addressline1 || "";
                //document.getElementById("address_line_2").value =
                address.addressline2 || "";
                //document.getElementById("posttown").value = address.posttown || "";
                //document.getElementById("postcode").value = address.postcode || "";
                document.getElementById("address").value = address.addressline1 + " " + address.posttown + " " + address.postcode;

                // Full list of response elements
                // https://postcoder.com/docs/address-lookup
            }
        </script>
    </body>
</html>

