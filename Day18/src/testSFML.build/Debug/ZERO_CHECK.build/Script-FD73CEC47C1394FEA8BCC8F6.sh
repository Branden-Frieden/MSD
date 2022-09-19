#!/bin/sh
set -e
if test "$CONFIGURATION" = "Debug"; then :
  cd /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src
  make -f /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "Release"; then :
  cd /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src
  make -f /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "MinSizeRel"; then :
  cd /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src
  make -f /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src/CMakeScripts/ReRunCMake.make
fi
if test "$CONFIGURATION" = "RelWithDebInfo"; then :
  cd /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src
  make -f /Users/brandenfrieden/MSD/CS6010/myGithubRepo/Day18/src/CMakeScripts/ReRunCMake.make
fi

