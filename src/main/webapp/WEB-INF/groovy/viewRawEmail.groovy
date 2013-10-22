def e = datastore.execute { select single from email }

response.contentType = "text/plain"

out << e.rawContent