#!/bin/bash
# Renames src/java1, java1_1..java1_4, java5..java9 to a zero-padded
# two-digit scheme so GitHub's alphabetical file listing shows them
# in true version order: java01, java01_1 ... java01_4, java05 ... java25.
#
# java10..java25 are left untouched — they already sort correctly
# once java01-java09 are two digits.
#
# Usage:
#   1. Copy this file into the ROOT of your repo (next to README.md)
#   2. Run:  bash rename-java-folders.sh
#   3. Review with:  git status
#   4. Commit:  git commit -m "Rename Java version folders for correct sequential sorting"
#   5. Push:    git push

set -e

cd src

git mv java1    java01
git mv java1_1  java01_1
git mv java1_2  java01_2
git mv java1_3  java01_3
git mv java1_4  java01_4
git mv java5    java05
git mv java6    java06
git mv java7    java07
git mv java8    java08
git mv java9    java09

cd ..

echo ""
echo "Done. Folders renamed:"
echo "  java1   -> java01"
echo "  java1_1 -> java01_1"
echo "  java1_2 -> java01_2"
echo "  java1_3 -> java01_3"
echo "  java1_4 -> java01_4"
echo "  java5   -> java05"
echo "  java6   -> java06"
echo "  java7   -> java07"
echo "  java8   -> java08"
echo "  java9   -> java09"
echo ""
echo "Next steps:"
echo "  1. Replace README.md with the updated version (links already fixed)"
echo "  2. git add -A"
echo "  3. git commit -m \"Rename Java version folders for correct sequential sorting\""
echo "  4. git push"
