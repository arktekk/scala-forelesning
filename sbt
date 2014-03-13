SBT_OPTS="-Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256M -Dapple.awt.UIElement=true"
java $SBT_OPTS -jar `dirname $0`/sbt-launch.jar "$@"