	.file	"DissassemblyLab.cpp"
# GNU C++ (Ubuntu 4.8.4-2ubuntu1~14.04.4) version 4.8.4 (x86_64-linux-gnu)
#	compiled by GNU C version 4.8.4, GMP version 5.1.3, MPFR version 3.1.2-p3, MPC version 1.0.1
# GGC heuristics: --param ggc-min-expand=100 --param ggc-min-heapsize=131072
# options passed:  -imultiarch x86_64-linux-gnu -D_GNU_SOURCE
# DissassemblyLab.cpp -mtune=generic -march=x86-64
# -auxbase-strip DissassemblyLabO0.s -g -O0 -fverbose-asm -fstack-protector
# -Wformat -Wformat-security
# options enabled:  -faggressive-loop-optimizations
# -fasynchronous-unwind-tables -fauto-inc-dec -fbranch-count-reg -fcommon
# -fdelete-null-pointer-checks -fdwarf2-cfi-asm -fearly-inlining
# -feliminate-unused-debug-types -fexceptions -ffunction-cse -fgcse-lm
# -fgnu-runtime -fgnu-unique -fident -finline-atomics -fira-hoist-pressure
# -fira-share-save-slots -fira-share-spill-slots -fivopts
# -fkeep-static-consts -fleading-underscore -fmath-errno
# -fmerge-debug-strings -fmove-loop-invariants -fpeephole
# -fprefetch-loop-arrays -freg-struct-return
# -fsched-critical-path-heuristic -fsched-dep-count-heuristic
# -fsched-group-heuristic -fsched-interblock -fsched-last-insn-heuristic
# -fsched-rank-heuristic -fsched-spec -fsched-spec-insn-heuristic
# -fsched-stalled-insns-dep -fshow-column -fsigned-zeros
# -fsplit-ivs-in-unroller -fstack-protector -fstrict-volatile-bitfields
# -fsync-libcalls -ftrapping-math -ftree-coalesce-vars -ftree-cselim
# -ftree-forwprop -ftree-loop-if-convert -ftree-loop-im -ftree-loop-ivcanon
# -ftree-loop-optimize -ftree-parallelize-loops= -ftree-phiprop -ftree-pta
# -ftree-reassoc -ftree-scev-cprop -ftree-slp-vectorize
# -ftree-vect-loop-version -funit-at-a-time -funwind-tables -fverbose-asm
# -fzero-initialized-in-bss -m128bit-long-double -m64 -m80387
# -maccumulate-outgoing-args -malign-stringops -mfancy-math-387
# -mfp-ret-in-387 -mfxsr -mglibc -mieee-fp -mlong-double-80 -mmmx -mno-sse4
# -mpush-args -mred-zone -msse -msse2 -mtls-direct-seg-refs

	.text
.Ltext0:
	.globl	_Z3addii
	.type	_Z3addii, @function
_Z3addii:
.LFB0:
	.file 1 "DissassemblyLab.cpp"
	.loc 1 2 0
	.cfi_startproc
	pushq	%rbp	#
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp	#,
	.cfi_def_cfa_register 6
	movl	%edi, -4(%rbp)	# a, a
	movl	%esi, -8(%rbp)	# b, b
	.loc 1 3 0
	movl	-8(%rbp), %eax	# b, tmp61
	movl	-4(%rbp), %edx	# a, tmp62
	addl	%edx, %eax	# tmp62, D.2784
	.loc 1 4 0
	popq	%rbp	#
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	_Z3addii, .-_Z3addii
	.section	.rodata
.LC0:
	.string	"%d"
	.text
	.globl	main
	.type	main, @function
main:
.LFB1:
	.loc 1 6 0
	.cfi_startproc
	pushq	%rbp	#
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp	#,
	.cfi_def_cfa_register 6
	.loc 1 7 0
	movl	$1, %esi	#,
	movl	$3, %edi	#,
	call	_Z3addii	#
	movl	%eax, %esi	# D.2785,
	movl	$.LC0, %edi	#,
	movl	$0, %eax	#,
	call	printf	#
	.loc 1 8 0
	movl	$0, %eax	#, D.2785
	.loc 1 9 0
	popq	%rbp	#
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE1:
	.size	main, .-main
.Letext0:
	.file 2 "/usr/lib/gcc/x86_64-linux-gnu/4.8/include/stddef.h"
	.file 3 "/usr/include/x86_64-linux-gnu/bits/types.h"
	.file 4 "/usr/include/stdio.h"
	.file 5 "/usr/include/libio.h"
	.file 6 "/usr/include/wchar.h"
	.file 7 "/usr/include/_G_config.h"
	.file 8 "/usr/include/c++/4.8/cstdio"
	.file 9 "<built-in>"
	.section	.debug_info,"",@progbits
.Ldebug_info0:
	.long	0x6e1
	.value	0x4
	.long	.Ldebug_abbrev0
	.byte	0x8
	.uleb128 0x1
	.long	.LASF84
	.byte	0x4
	.long	.LASF85
	.long	.LASF86
	.quad	.Ltext0
	.quad	.Letext0-.Ltext0
	.long	.Ldebug_line0
	.uleb128 0x2
	.long	.LASF7
	.byte	0x2
	.byte	0xd4
	.long	0x38
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF0
	.uleb128 0x3
	.byte	0x1
	.byte	0x8
	.long	.LASF1
	.uleb128 0x3
	.byte	0x2
	.byte	0x7
	.long	.LASF2
	.uleb128 0x3
	.byte	0x4
	.byte	0x7
	.long	.LASF3
	.uleb128 0x3
	.byte	0x1
	.byte	0x6
	.long	.LASF4
	.uleb128 0x3
	.byte	0x2
	.byte	0x5
	.long	.LASF5
	.uleb128 0x4
	.byte	0x4
	.byte	0x5
	.string	"int"
	.uleb128 0x3
	.byte	0x8
	.byte	0x5
	.long	.LASF6
	.uleb128 0x2
	.long	.LASF8
	.byte	0x3
	.byte	0x83
	.long	0x69
	.uleb128 0x2
	.long	.LASF9
	.byte	0x3
	.byte	0x84
	.long	0x69
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF10
	.uleb128 0x5
	.byte	0x8
	.uleb128 0x6
	.byte	0x8
	.long	0x95
	.uleb128 0x3
	.byte	0x1
	.byte	0x6
	.long	.LASF11
	.uleb128 0x2
	.long	.LASF12
	.byte	0x4
	.byte	0x30
	.long	0xa7
	.uleb128 0x7
	.long	.LASF52
	.byte	0xd8
	.byte	0x5
	.byte	0xf5
	.long	0x227
	.uleb128 0x8
	.long	.LASF13
	.byte	0x5
	.byte	0xf6
	.long	0x62
	.byte	0
	.uleb128 0x8
	.long	.LASF14
	.byte	0x5
	.byte	0xfb
	.long	0x8f
	.byte	0x8
	.uleb128 0x8
	.long	.LASF15
	.byte	0x5
	.byte	0xfc
	.long	0x8f
	.byte	0x10
	.uleb128 0x8
	.long	.LASF16
	.byte	0x5
	.byte	0xfd
	.long	0x8f
	.byte	0x18
	.uleb128 0x8
	.long	.LASF17
	.byte	0x5
	.byte	0xfe
	.long	0x8f
	.byte	0x20
	.uleb128 0x8
	.long	.LASF18
	.byte	0x5
	.byte	0xff
	.long	0x8f
	.byte	0x28
	.uleb128 0x9
	.long	.LASF19
	.byte	0x5
	.value	0x100
	.long	0x8f
	.byte	0x30
	.uleb128 0x9
	.long	.LASF20
	.byte	0x5
	.value	0x101
	.long	0x8f
	.byte	0x38
	.uleb128 0x9
	.long	.LASF21
	.byte	0x5
	.value	0x102
	.long	0x8f
	.byte	0x40
	.uleb128 0x9
	.long	.LASF22
	.byte	0x5
	.value	0x104
	.long	0x8f
	.byte	0x48
	.uleb128 0x9
	.long	.LASF23
	.byte	0x5
	.value	0x105
	.long	0x8f
	.byte	0x50
	.uleb128 0x9
	.long	.LASF24
	.byte	0x5
	.value	0x106
	.long	0x8f
	.byte	0x58
	.uleb128 0x9
	.long	.LASF25
	.byte	0x5
	.value	0x108
	.long	0x2ee
	.byte	0x60
	.uleb128 0x9
	.long	.LASF26
	.byte	0x5
	.value	0x10a
	.long	0x2f4
	.byte	0x68
	.uleb128 0x9
	.long	.LASF27
	.byte	0x5
	.value	0x10c
	.long	0x62
	.byte	0x70
	.uleb128 0x9
	.long	.LASF28
	.byte	0x5
	.value	0x110
	.long	0x62
	.byte	0x74
	.uleb128 0x9
	.long	.LASF29
	.byte	0x5
	.value	0x112
	.long	0x70
	.byte	0x78
	.uleb128 0x9
	.long	.LASF30
	.byte	0x5
	.value	0x116
	.long	0x46
	.byte	0x80
	.uleb128 0x9
	.long	.LASF31
	.byte	0x5
	.value	0x117
	.long	0x54
	.byte	0x82
	.uleb128 0x9
	.long	.LASF32
	.byte	0x5
	.value	0x118
	.long	0x2fa
	.byte	0x83
	.uleb128 0x9
	.long	.LASF33
	.byte	0x5
	.value	0x11c
	.long	0x30a
	.byte	0x88
	.uleb128 0x9
	.long	.LASF34
	.byte	0x5
	.value	0x125
	.long	0x7b
	.byte	0x90
	.uleb128 0x9
	.long	.LASF35
	.byte	0x5
	.value	0x12e
	.long	0x8d
	.byte	0x98
	.uleb128 0x9
	.long	.LASF36
	.byte	0x5
	.value	0x12f
	.long	0x8d
	.byte	0xa0
	.uleb128 0x9
	.long	.LASF37
	.byte	0x5
	.value	0x130
	.long	0x8d
	.byte	0xa8
	.uleb128 0x9
	.long	.LASF38
	.byte	0x5
	.value	0x131
	.long	0x8d
	.byte	0xb0
	.uleb128 0x9
	.long	.LASF39
	.byte	0x5
	.value	0x132
	.long	0x2d
	.byte	0xb8
	.uleb128 0x9
	.long	.LASF40
	.byte	0x5
	.value	0x134
	.long	0x62
	.byte	0xc0
	.uleb128 0x9
	.long	.LASF41
	.byte	0x5
	.value	0x136
	.long	0x310
	.byte	0xc4
	.byte	0
	.uleb128 0xa
	.byte	0x8
	.byte	0x6
	.byte	0x53
	.long	.LASF47
	.long	0x26b
	.uleb128 0xb
	.byte	0x4
	.byte	0x6
	.byte	0x56
	.long	0x252
	.uleb128 0xc
	.long	.LASF42
	.byte	0x6
	.byte	0x58
	.long	0x4d
	.uleb128 0xc
	.long	.LASF43
	.byte	0x6
	.byte	0x5c
	.long	0x26b
	.byte	0
	.uleb128 0x8
	.long	.LASF44
	.byte	0x6
	.byte	0x54
	.long	0x62
	.byte	0
	.uleb128 0x8
	.long	.LASF45
	.byte	0x6
	.byte	0x5d
	.long	0x233
	.byte	0x4
	.byte	0
	.uleb128 0xd
	.long	0x95
	.long	0x27b
	.uleb128 0xe
	.long	0x86
	.byte	0x3
	.byte	0
	.uleb128 0x2
	.long	.LASF46
	.byte	0x6
	.byte	0x5e
	.long	0x227
	.uleb128 0xa
	.byte	0x10
	.byte	0x7
	.byte	0x16
	.long	.LASF48
	.long	0x2ab
	.uleb128 0x8
	.long	.LASF49
	.byte	0x7
	.byte	0x17
	.long	0x70
	.byte	0
	.uleb128 0x8
	.long	.LASF50
	.byte	0x7
	.byte	0x18
	.long	0x27b
	.byte	0x8
	.byte	0
	.uleb128 0x2
	.long	.LASF51
	.byte	0x7
	.byte	0x19
	.long	0x286
	.uleb128 0xf
	.long	.LASF87
	.byte	0x5
	.byte	0x9a
	.uleb128 0x7
	.long	.LASF53
	.byte	0x18
	.byte	0x5
	.byte	0xa0
	.long	0x2ee
	.uleb128 0x8
	.long	.LASF54
	.byte	0x5
	.byte	0xa1
	.long	0x2ee
	.byte	0
	.uleb128 0x8
	.long	.LASF55
	.byte	0x5
	.byte	0xa2
	.long	0x2f4
	.byte	0x8
	.uleb128 0x8
	.long	.LASF56
	.byte	0x5
	.byte	0xa6
	.long	0x62
	.byte	0x10
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x2bd
	.uleb128 0x6
	.byte	0x8
	.long	0xa7
	.uleb128 0xd
	.long	0x95
	.long	0x30a
	.uleb128 0xe
	.long	0x86
	.byte	0
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x2b6
	.uleb128 0xd
	.long	0x95
	.long	0x320
	.uleb128 0xe
	.long	0x86
	.byte	0x13
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x326
	.uleb128 0x10
	.long	0x95
	.uleb128 0x2
	.long	.LASF57
	.byte	0x4
	.byte	0x6e
	.long	0x2ab
	.uleb128 0x11
	.string	"std"
	.byte	0x9
	.byte	0
	.long	0x406
	.uleb128 0x12
	.byte	0x8
	.byte	0x60
	.long	0x9c
	.uleb128 0x12
	.byte	0x8
	.byte	0x61
	.long	0x32b
	.uleb128 0x12
	.byte	0x8
	.byte	0x63
	.long	0x406
	.uleb128 0x12
	.byte	0x8
	.byte	0x64
	.long	0x41e
	.uleb128 0x12
	.byte	0x8
	.byte	0x65
	.long	0x433
	.uleb128 0x12
	.byte	0x8
	.byte	0x66
	.long	0x449
	.uleb128 0x12
	.byte	0x8
	.byte	0x67
	.long	0x45f
	.uleb128 0x12
	.byte	0x8
	.byte	0x68
	.long	0x474
	.uleb128 0x12
	.byte	0x8
	.byte	0x69
	.long	0x48a
	.uleb128 0x12
	.byte	0x8
	.byte	0x6a
	.long	0x4ab
	.uleb128 0x12
	.byte	0x8
	.byte	0x6b
	.long	0x4cb
	.uleb128 0x12
	.byte	0x8
	.byte	0x6f
	.long	0x4e6
	.uleb128 0x12
	.byte	0x8
	.byte	0x70
	.long	0x50b
	.uleb128 0x12
	.byte	0x8
	.byte	0x72
	.long	0x52b
	.uleb128 0x12
	.byte	0x8
	.byte	0x73
	.long	0x54b
	.uleb128 0x12
	.byte	0x8
	.byte	0x74
	.long	0x571
	.uleb128 0x12
	.byte	0x8
	.byte	0x76
	.long	0x587
	.uleb128 0x12
	.byte	0x8
	.byte	0x77
	.long	0x59d
	.uleb128 0x12
	.byte	0x8
	.byte	0x78
	.long	0x5a9
	.uleb128 0x12
	.byte	0x8
	.byte	0x79
	.long	0x5bf
	.uleb128 0x12
	.byte	0x8
	.byte	0x7e
	.long	0x5d1
	.uleb128 0x12
	.byte	0x8
	.byte	0x7f
	.long	0x5e6
	.uleb128 0x12
	.byte	0x8
	.byte	0x80
	.long	0x600
	.uleb128 0x12
	.byte	0x8
	.byte	0x82
	.long	0x612
	.uleb128 0x12
	.byte	0x8
	.byte	0x83
	.long	0x629
	.uleb128 0x12
	.byte	0x8
	.byte	0x86
	.long	0x64e
	.uleb128 0x12
	.byte	0x8
	.byte	0x87
	.long	0x659
	.uleb128 0x12
	.byte	0x8
	.byte	0x88
	.long	0x66e
	.byte	0
	.uleb128 0x13
	.long	.LASF73
	.byte	0x4
	.value	0x33a
	.long	0x418
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x9c
	.uleb128 0x15
	.long	.LASF58
	.byte	0x4
	.byte	0xed
	.long	0x62
	.long	0x433
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF59
	.byte	0x4
	.value	0x33c
	.long	0x62
	.long	0x449
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF60
	.byte	0x4
	.value	0x33e
	.long	0x62
	.long	0x45f
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x15
	.long	.LASF61
	.byte	0x4
	.byte	0xf2
	.long	0x62
	.long	0x474
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF62
	.byte	0x4
	.value	0x213
	.long	0x62
	.long	0x48a
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF63
	.byte	0x4
	.value	0x31e
	.long	0x62
	.long	0x4a5
	.uleb128 0x14
	.long	0x418
	.uleb128 0x14
	.long	0x4a5
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x32b
	.uleb128 0x16
	.long	.LASF64
	.byte	0x4
	.value	0x26e
	.long	0x8f
	.long	0x4cb
	.uleb128 0x14
	.long	0x8f
	.uleb128 0x14
	.long	0x62
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF65
	.byte	0x4
	.value	0x110
	.long	0x418
	.long	0x4e6
	.uleb128 0x14
	.long	0x320
	.uleb128 0x14
	.long	0x320
	.byte	0
	.uleb128 0x16
	.long	.LASF66
	.byte	0x4
	.value	0x2c5
	.long	0x2d
	.long	0x50b
	.uleb128 0x14
	.long	0x8d
	.uleb128 0x14
	.long	0x2d
	.uleb128 0x14
	.long	0x2d
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF67
	.byte	0x4
	.value	0x116
	.long	0x418
	.long	0x52b
	.uleb128 0x14
	.long	0x320
	.uleb128 0x14
	.long	0x320
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF68
	.byte	0x4
	.value	0x2ed
	.long	0x62
	.long	0x54b
	.uleb128 0x14
	.long	0x418
	.uleb128 0x14
	.long	0x69
	.uleb128 0x14
	.long	0x62
	.byte	0
	.uleb128 0x16
	.long	.LASF69
	.byte	0x4
	.value	0x323
	.long	0x62
	.long	0x566
	.uleb128 0x14
	.long	0x418
	.uleb128 0x14
	.long	0x566
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x56c
	.uleb128 0x10
	.long	0x32b
	.uleb128 0x16
	.long	.LASF70
	.byte	0x4
	.value	0x2f2
	.long	0x69
	.long	0x587
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x16
	.long	.LASF71
	.byte	0x4
	.value	0x214
	.long	0x62
	.long	0x59d
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x17
	.long	.LASF80
	.byte	0x4
	.value	0x21a
	.long	0x62
	.uleb128 0x16
	.long	.LASF72
	.byte	0x4
	.value	0x27e
	.long	0x8f
	.long	0x5bf
	.uleb128 0x14
	.long	0x8f
	.byte	0
	.uleb128 0x13
	.long	.LASF74
	.byte	0x4
	.value	0x34e
	.long	0x5d1
	.uleb128 0x14
	.long	0x320
	.byte	0
	.uleb128 0x15
	.long	.LASF75
	.byte	0x4
	.byte	0xb2
	.long	0x62
	.long	0x5e6
	.uleb128 0x14
	.long	0x320
	.byte	0
	.uleb128 0x15
	.long	.LASF76
	.byte	0x4
	.byte	0xb4
	.long	0x62
	.long	0x600
	.uleb128 0x14
	.long	0x320
	.uleb128 0x14
	.long	0x320
	.byte	0
	.uleb128 0x13
	.long	.LASF77
	.byte	0x4
	.value	0x2f7
	.long	0x612
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x13
	.long	.LASF78
	.byte	0x4
	.value	0x14c
	.long	0x629
	.uleb128 0x14
	.long	0x418
	.uleb128 0x14
	.long	0x8f
	.byte	0
	.uleb128 0x16
	.long	.LASF79
	.byte	0x4
	.value	0x150
	.long	0x62
	.long	0x64e
	.uleb128 0x14
	.long	0x418
	.uleb128 0x14
	.long	0x8f
	.uleb128 0x14
	.long	0x62
	.uleb128 0x14
	.long	0x2d
	.byte	0
	.uleb128 0x18
	.long	.LASF81
	.byte	0x4
	.byte	0xc3
	.long	0x418
	.uleb128 0x15
	.long	.LASF82
	.byte	0x4
	.byte	0xd1
	.long	0x8f
	.long	0x66e
	.uleb128 0x14
	.long	0x8f
	.byte	0
	.uleb128 0x16
	.long	.LASF83
	.byte	0x4
	.value	0x2be
	.long	0x62
	.long	0x689
	.uleb128 0x14
	.long	0x62
	.uleb128 0x14
	.long	0x418
	.byte	0
	.uleb128 0x19
	.string	"add"
	.byte	0x1
	.byte	0x2
	.long	.LASF88
	.long	0x62
	.quad	.LFB0
	.quad	.LFE0-.LFB0
	.uleb128 0x1
	.byte	0x9c
	.long	0x6c7
	.uleb128 0x1a
	.string	"a"
	.byte	0x1
	.byte	0x2
	.long	0x62
	.uleb128 0x2
	.byte	0x91
	.sleb128 -20
	.uleb128 0x1a
	.string	"b"
	.byte	0x1
	.byte	0x2
	.long	0x62
	.uleb128 0x2
	.byte	0x91
	.sleb128 -24
	.byte	0
	.uleb128 0x1b
	.long	.LASF89
	.byte	0x1
	.byte	0x6
	.long	0x62
	.quad	.LFB1
	.quad	.LFE1-.LFB1
	.uleb128 0x1
	.byte	0x9c
	.byte	0
	.section	.debug_abbrev,"",@progbits
.Ldebug_abbrev0:
	.uleb128 0x1
	.uleb128 0x11
	.byte	0x1
	.uleb128 0x25
	.uleb128 0xe
	.uleb128 0x13
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x1b
	.uleb128 0xe
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x10
	.uleb128 0x17
	.byte	0
	.byte	0
	.uleb128 0x2
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x3
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0xe
	.byte	0
	.byte	0
	.uleb128 0x4
	.uleb128 0x24
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3e
	.uleb128 0xb
	.uleb128 0x3
	.uleb128 0x8
	.byte	0
	.byte	0
	.uleb128 0x5
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x6
	.uleb128 0xf
	.byte	0
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x7
	.uleb128 0x13
	.byte	0x1
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x8
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x38
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x9
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x38
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xa
	.uleb128 0x13
	.byte	0x1
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xb
	.uleb128 0x17
	.byte	0x1
	.uleb128 0xb
	.uleb128 0xb
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xc
	.uleb128 0xd
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xd
	.uleb128 0x1
	.byte	0x1
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0xe
	.uleb128 0x21
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2f
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0xf
	.uleb128 0x16
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x10
	.uleb128 0x26
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x11
	.uleb128 0x39
	.byte	0x1
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x12
	.uleb128 0x8
	.byte	0
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x18
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x13
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x14
	.uleb128 0x5
	.byte	0
	.uleb128 0x49
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x15
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x16
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x17
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0x5
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x18
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x19
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x6e
	.uleb128 0xe
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2117
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1a
	.uleb128 0x5
	.byte	0
	.uleb128 0x3
	.uleb128 0x8
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x1b
	.uleb128 0x2e
	.byte	0
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x40
	.uleb128 0x18
	.uleb128 0x2116
	.uleb128 0x19
	.byte	0
	.byte	0
	.byte	0
	.section	.debug_aranges,"",@progbits
	.long	0x2c
	.value	0x2
	.long	.Ldebug_info0
	.byte	0x8
	.byte	0
	.value	0
	.value	0
	.quad	.Ltext0
	.quad	.Letext0-.Ltext0
	.quad	0
	.quad	0
	.section	.debug_line,"",@progbits
.Ldebug_line0:
	.section	.debug_str,"MS",@progbits,1
.LASF21:
	.string	"_IO_buf_end"
.LASF65:
	.string	"fopen"
.LASF29:
	.string	"_old_offset"
.LASF61:
	.string	"fflush"
.LASF46:
	.string	"__mbstate_t"
.LASF42:
	.string	"__wch"
.LASF45:
	.string	"__value"
.LASF24:
	.string	"_IO_save_end"
.LASF5:
	.string	"short int"
.LASF7:
	.string	"size_t"
.LASF49:
	.string	"__pos"
.LASF10:
	.string	"sizetype"
.LASF34:
	.string	"_offset"
.LASF89:
	.string	"main"
.LASF18:
	.string	"_IO_write_ptr"
.LASF13:
	.string	"_flags"
.LASF62:
	.string	"fgetc"
.LASF20:
	.string	"_IO_buf_base"
.LASF67:
	.string	"freopen"
.LASF25:
	.string	"_markers"
.LASF15:
	.string	"_IO_read_end"
.LASF51:
	.string	"_G_fpos_t"
.LASF64:
	.string	"fgets"
.LASF44:
	.string	"__count"
.LASF71:
	.string	"getc"
.LASF63:
	.string	"fgetpos"
.LASF79:
	.string	"setvbuf"
.LASF58:
	.string	"fclose"
.LASF75:
	.string	"remove"
.LASF78:
	.string	"setbuf"
.LASF68:
	.string	"fseek"
.LASF86:
	.string	"/home/bcfried/MSD/CS6013"
.LASF80:
	.string	"getchar"
.LASF33:
	.string	"_lock"
.LASF6:
	.string	"long int"
.LASF72:
	.string	"gets"
.LASF30:
	.string	"_cur_column"
.LASF74:
	.string	"perror"
.LASF56:
	.string	"_pos"
.LASF70:
	.string	"ftell"
.LASF59:
	.string	"feof"
.LASF55:
	.string	"_sbuf"
.LASF52:
	.string	"_IO_FILE"
.LASF57:
	.string	"fpos_t"
.LASF73:
	.string	"clearerr"
.LASF88:
	.string	"_Z3addii"
.LASF1:
	.string	"unsigned char"
.LASF43:
	.string	"__wchb"
.LASF4:
	.string	"signed char"
.LASF84:
	.string	"GNU C++ 4.8.4 -mtune=generic -march=x86-64 -g -O0 -fstack-protector"
.LASF53:
	.string	"_IO_marker"
.LASF32:
	.string	"_shortbuf"
.LASF17:
	.string	"_IO_write_base"
.LASF41:
	.string	"_unused2"
.LASF14:
	.string	"_IO_read_ptr"
.LASF2:
	.string	"short unsigned int"
.LASF11:
	.string	"char"
.LASF85:
	.string	"DissassemblyLab.cpp"
.LASF54:
	.string	"_next"
.LASF35:
	.string	"__pad1"
.LASF36:
	.string	"__pad2"
.LASF37:
	.string	"__pad3"
.LASF38:
	.string	"__pad4"
.LASF39:
	.string	"__pad5"
.LASF69:
	.string	"fsetpos"
.LASF0:
	.string	"long unsigned int"
.LASF19:
	.string	"_IO_write_end"
.LASF9:
	.string	"__off64_t"
.LASF8:
	.string	"__off_t"
.LASF26:
	.string	"_chain"
.LASF50:
	.string	"__state"
.LASF47:
	.string	"11__mbstate_t"
.LASF23:
	.string	"_IO_backup_base"
.LASF76:
	.string	"rename"
.LASF48:
	.string	"9_G_fpos_t"
.LASF28:
	.string	"_flags2"
.LASF40:
	.string	"_mode"
.LASF16:
	.string	"_IO_read_base"
.LASF82:
	.string	"tmpnam"
.LASF31:
	.string	"_vtable_offset"
.LASF22:
	.string	"_IO_save_base"
.LASF77:
	.string	"rewind"
.LASF27:
	.string	"_fileno"
.LASF12:
	.string	"FILE"
.LASF3:
	.string	"unsigned int"
.LASF66:
	.string	"fread"
.LASF83:
	.string	"ungetc"
.LASF60:
	.string	"ferror"
.LASF87:
	.string	"_IO_lock_t"
.LASF81:
	.string	"tmpfile"
	.ident	"GCC: (Ubuntu 4.8.4-2ubuntu1~14.04.4) 4.8.4"
	.section	.note.GNU-stack,"",@progbits
