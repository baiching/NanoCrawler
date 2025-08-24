@echo off
echo Starting Solr in foreground...
echo [Press Ctrl+C to stop Solr when done]
echo.
cd ../lib/solr-9.9.0/solr-9.9.0/bin/
solr start -p 8983
pause