<form class="md-form" action="./controller" name="command" method="post" enctype="multipart/form-data">
    <div class="modal fade" id="changeAlbum${vs.index}" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="label.changeAlbum"
                                                                                    bundle="${var}"/></h5>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="albumName" placeholder="${album.albumName}" required>
                </div>
                <div class="modal-body">
                    <input type="text" class="form-control" name="albumInfo" placeholder="${album.informationAboutAlbum}" required>
                </div>
                <br/>
                <div class="modal-body">
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="file" name="file" placeholder="<fmt:message
                                    key="label.uploadFile"
                                    bundle="${var}"/>">
                        <input type="hidden" name="albumId" value="${album.id}">
                        <input type="hidden" name="command" value="change_album"/>
                        <input type="hidden" name="target" value="album/">
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-success btn-lg btn-block"><fmt:message
                                    key="label.change"
                                    bundle="${var}"/></button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
