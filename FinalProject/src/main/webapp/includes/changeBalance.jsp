<button type="button" style="float: right" class="btn btn-dark btn-sm" data-toggle="modal" data-target="#changeBalance">
    <fmt:message key="label.change" bundle="${var}"/>
</button>

<form action="./controller" name="command" method="post">
    <div class="modal fade" id="changeBalance" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="card-body">
                        <h3 class="text-center"><fmt:message key="label.creditCartInfo" bundle="${var}"/></h3>
                        <hr>
                        <div class="form-group">
                            <label><fmt:message key="label.cartHoldersName" bundle="${var}"/></label>
                            <input type="text" class="form-control" pattern="\w+ \w+.*" autocomplete="off"
                                   required="required">
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="label.cartNumber" bundle="${var}"/></label>
                            <input type="text" class="form-control" maxlength="20" autocomplete="off"
                                   pattern="\d{16}" required="required">
                        </div>
                        <div class="form-group row">
                            <label class="col-md-12"><fmt:message key="label.cartExpDate" bundle="${var}"/></label>
                            <div class="col-md-4">
                                <select class="form-control" name="cc_exp_mo" size="0">
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09">09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <select class="form-control" name="cc_exp_yr" size="0">
                                    <option>2021</option>
                                    <option>2022</option>
                                    <option>2023</option>
                                    <option>2024</option>
                                    <option>2025</option>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="text" class="form-control" maxlength="3"
                                       pattern="\d{3}" required="required"
                                       placeholder="CVC">
                            </div>
                        </div>
                        <div class="row">
                            <label class="col-md-12"><fmt:message key="label.amount" bundle="${var}"/></label>
                        </div>
                        <div class="form-group">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text">$</span>
                                </div>
                                <input type="text" class="form-control text-right" name="amount" maxlength="5"
                                       placeholder="0">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-lg btn-block" data-dismiss="modal"><fmt:message
                            key="label.close"
                            bundle="${var}"/></button>
                    <input type="hidden" name="command" value="change_balance"/>
                    <button type="submit" class="btn btn-success btn-lg btn-block"><fmt:message key="label.saveChanges"
                                                                                                bundle="${var}"/></button>
                </div>
            </div>
        </div>
    </div>
</form>