ARCH ?= ppc
OSX_VER ?= 10.3

CFLAGS ?= -I /System/Library/Frameworks/JavaVM.framework/Headers/

../resources/libnet.dylib:
	gcc uk_co_therhys_JReddit_Net_MacNet.m -mmacosx-version-min=${OSX_VER} -arch ${ARCH} -fPIC -dynamiclib -o ../resources/libnet.dylib ${CFLAGS} -framework Foundation
