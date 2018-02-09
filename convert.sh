#!/bin/bash

for file in **/*.java; do
    echo $file
    iconv -c -f utf-8 -t ascii $file > $file.tmp
    mv -f $file.tmp $file
done
