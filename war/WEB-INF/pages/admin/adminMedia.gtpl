<!DOCTYPE html>
<html>
<% import com.google.appengine.api.blobstore.BlobInfoFactory %>

<head>
    <title>Media browser -- Guillaume Laforge -- Groovy Blog</title>
	<% include '/WEB-INF/includes/meta.gtpl' %>

    <link rel="stylesheet" type="text/css" href="/css/chosen.css">

    <script type="text/javascript" src="/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="/js/chosen.jquery.min.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function() {
            jQuery(".chzn-select").chosen();

            var wrapper = jQuery('<div/>').css({ height: 0, width: 0, 'overflow': 'hidden' });
            var fileInput = jQuery(':file').wrap(wrapper);

            fileInput.change(function() {
                var that = jQuery(this);
                jQuery('#fileName').val(that.val());
            });

            jQuery('#fileSelector').click(function() {
                fileInput.click();
            });

            jQuery('#file').click(function(){
                fileInput.click();
            }).show();
        });
    </script>

</head>

<body id="top">

<% include '/WEB-INF/includes/header.gtpl' %>
<% include '/WEB-INF/includes/navigation.gtpl' %>

<div id="content-wrapper">
	<div class="center-wrapper">

		<div class="content" id="content-two-columns">

			<div id="main-wrapper">
				<div id="main">

					<div class="post">

						<div class="post-title"><h1>Media browser</h1></div>

						<div class="post-body">

                            <fieldset>
                                <h3>Store new media</h3>
                                <br>
                                <form action="${blobstore.createUploadUrl('/admin/media/add')}" method="post" enctype="multipart/form-data">
                                    <div class="form-row">
                                        <div class="form-value">
                                            <input type="button" id="fileSelector" class="button" value="Choose a file &#187;" />
                                            &nbsp;
                                            <input type="text" name="fileName" id="fileName" class="text" disabled="disabled" size="57">
                                            <input type="file" name="media" style="visibility: hidden" />
                                        </div>
                                        <div class="clearer">&nbsp;</div>
                                    </div>
                                    <br>
                                    <div class="form-row form-row-submit">
                                        <div class="form-value"><input type="submit" class="button" value="Upload &#187;" /></div>
                                        <div class="clearer">&nbsp;</div>
                                    </div>
                                </form>
                            </fieldset>

                            <a name="media-browser"></a>
                            <br>
                            <div id='media-browser'>
                            <%
                                def blobstoreKind = com.google.appengine.api.blobstore.BlobInfoFactory.KIND
                                def numberPerPage = 8

                                def page = params.page ? params.page.toInteger() : 0
                                def offsetParam = page * numberPerPage

                                def medias = datastore.execute {
                                    from blobstoreKind
                                    offset offsetParam limit numberPerPage
                                    sort desc by creation
                                }
                                medias.each { media ->
                                    def mediaUrl = "/media/${java.net.URLEncoder.encode(media.filename)}"
                            %>
                                <div class="centered">
                                    <div class="framed thumbnail-container">
                                        <a href="${mediaUrl}">
                                            <%
                                                def blobInfo = new com.google.appengine.api.blobstore.BlobInfoFactory().createBlobInfo(media)
                                                def key = blobInfo.blobKey
                                        %>
                                            <img src="${images.getServingUrl(key, 100, true)}">
                                        </a>
                                    </div>
                                        <form action="/admin/media/delete/${key.keyString}" method="post">
                                            <input type="hidden" name="page" value="${page}">
                                            <input type="hidden" name="singleOnPage" value="${medias.size() == 1}">
                                            <input type="image" src="/images/cross.png" alt="Delete" align="left"
                                                style="margin-top: 10px;">
                                            <div class="caption">
                                                <b>${blobInfo.filename}</b><br>
                                                ${blobInfo.creation.format('yyyy/MM/dd')} &mdash; ${blobInfo.size.intdiv(1024)} KB
                                            </div>
                                        </form>
                                </div>
                            <%  } %>
                                <p>&nbsp;</p>
                                <div class="archive-separator"></div>
                                <div id="prevNext">
                            <%
                                int entityCount = datastore.execute { select count from blobstoreKind }
                                int pageCount = (entityCount - 1).intdiv(numberPerPage) + 1

                                log.info "entity count = ${entityCount}"
                                log.info "page count = ${pageCount}"

                                boolean hasPrev = offsetParam > 0
                                boolean hasNext = page + 1 < pageCount

                            %>
                                    <input type="button" id="prevButton" class="button" value="&#171; Previous"
                                           onclick='window.location = "/admin/media${ page - 1 == 0 ? '' : '/p' + (page - 1) }#media-browser"'
                                           ${hasPrev ? '' : 'disabled'}
                                    />
                                    &nbsp;
                                    <input type="text" id="currentPage" class="text" value="Page ${page + 1} / ${pageCount}" disabled size="8" style="text-align: center">
                                    &nbsp;
                                    <input type="button" id="nextButton" class="button" value="Next &#187;"
                                           onclick='window.location = "/admin/media/p${page + 1}#media-browser"'
                                           ${hasNext ? '' : 'disabled'}
                                    />
                                </div>
                            </div>

                        </div>

                    </div>

				</div>
			</div>

            <% include '/WEB-INF/includes/left.gtpl' %>
		</div>

	</div>
</div>

<% include '/WEB-INF/includes/footer.gtpl' %>
<% include '/WEB-INF/includes/bottom.gtpl' %>

</body>
</html>