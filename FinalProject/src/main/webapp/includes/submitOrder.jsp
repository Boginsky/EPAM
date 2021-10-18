<button style="float: right" type="button" class="btn btn-dark btn-sm" data-toggle="modal" data-target="#submitOrder">
    <fmt:message key="label.checkout" bundle="${var}"/>
</button>

<form action="./controller" name="command" method="post">
    <div class="modal fade" id="submitOrder" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.enterAmount"
                                                                                    bundle="${var}"/></h5>
                </div>
                <div class="modal-header">
                    <h8 class="modal-title"><fmt:message key="label.yourBalance" bundle="${var}"/>
                        ${user.balance}$ ${user.bonus} <fmt:message key="label.bonus" bundle="${var}"/></h8>
                </div>
                <div class="modal-body">
                    <input type="number" class="form-control" name="moneyForPayment" placeholder=<fmt:message key="label.money" bundle="${var}"/> min="0" max="${total}" required>
                </div>
                <div class="modal-body">
                    <input type="number" class="form-control" name="bonusForPayment" placeholder=<fmt:message key="label.bonuses" bundle="${var}"/> min="0" max="${total}"
                           max="${user.bonus}" required>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-lg btn-block" data-dismiss="modal"><fmt:message key="label.close"
                                                                                                                     bundle="${var}"/></button>
                    <input type="hidden" name="command" value="submit_order"/>
                    <input type="hidden" name="total" value="${total}">
                    <button type="submit" class="btn btn-success btn-lg btn-block"><fmt:message key="label.submitOrder"
                                                                                                bundle="${var}"/></button>
                </div>
            </div>
        </div>
    </div>
</form>