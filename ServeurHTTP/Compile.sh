src="src"
lib="lib"
bin="bin"
libname="SERVEURHTTP"
resources="resources"
temp_src="temp_src"

mkdir -p "$temp_src"

find "$src" -type f -name "*.java" -exec cp {} "$temp_src/" \;

javac -cp "$lib/*:$resources" -d "$bin" "$temp_src"/*.java

rm -rf "$temp_src"