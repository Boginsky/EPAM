<form action="./controller" name="command" method="post">
    <div class="modal fade" id="addBonuses${vs.index}" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.enterAmountOfBonuses"
                                                                                    bundle="${var}"/></h5>
                </div>
                <div class="modal-body">
                    <input type="number" class="form-control" name="addedBonuses"
                           placeholder="0" min="0" max="1000"
                           required>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-default btn-lg btn-block" data-dismiss="modal"><fmt:message
                            key="label.close"
                            bundle="${var}"/></button>

                    <input type="hidden" name="command" value="change_bonuses"/>
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <input type="hidden" name="userBonuses" value="${user.bonus}"/>
                    <button type="submit" class="btn btn-dark btn-lg btn-block"><fmt:message key="label.saveChanges"
                                                                                             bundle="${var}"/></button>
                </div>
            </div>
        </div>
    </div>
</form>