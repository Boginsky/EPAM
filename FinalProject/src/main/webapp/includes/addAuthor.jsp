<form class="md-form" action="./controller" name="command" method="post" enctype="multipart/form-data">
    <div class="modal fade" id="addAuthor" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.addAuthor"
                                                                                    bundle="${var}"/></h5>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="authorName" placeholder="<fmt:message key="label.authorName"
                                                                                                      bundle="${var}"/>" required>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="authorInfo" placeholder="<fmt:message key="label.authorInfo"
                                                                                                      bundle="${var}"/>" required>
                </div>
                <br/>
                <div class="modal-body">
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="file" name="file" placeholder="Upload file">
                        <input type="hidden" name="command" value="add_author"/>
                        <input type="hidden" name="target" value="author/">
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success btn-lg btn-block"><fmt:message
                                    key="label.add"
                                    bundle="${var}"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
