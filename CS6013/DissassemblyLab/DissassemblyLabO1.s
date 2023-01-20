	.file	"DissassemblyLab.cpp"
# GNU C++ (Ubuntu 4.8.4-2ubuntu1~14.04.4) version 4.8.4 (x86_64-linux-gnu)
#	compiled by GNU C version 4.8.4, GMP version 5.1.3, MPFR version 3.1.2-p3, MPC version 1.0.1
# GGC heuristics: --param ggc-min-expand=100 --param ggc-min-heapsize=131072
# options passed:  -imultiarch x86_64-linux-gnu -D_GNU_SOURCE
# DissassemblyLab.cpp -mtune=generic -march=x86-64
# -auxbase-strip DissassemblyLabO1.s -g -O2 -fverbose-asm -fstack-protector
# -Wformat -Wformat-security
# options enabled:  -faggressive-loop-optimizations
# -fasynchronous-unwind-tables -fauto-inc-dec -fbranch-count-reg
# -fcaller-saves -fcombine-stack-adjustments -fcommon -fcompare-elim
# -fcprop-registers -fcrossjumping -fcse-follow-jumps -fdefer-pop
# -fdelete-null-pointer-checks -fdevirtualize -fdwarf2-cfi-asm
# -fearly-inlining -feliminate-unused-debug-types -fexceptions
# -fexpensive-optimizations -fforward-propagate -ffunction-cse -fgcse
# -fgcse-lm -fgnu-runtime -fgnu-unique -fguess-branch-probability
# -fhoist-adjacent-loads -fident -fif-conversion -fif-conversion2
# -findirect-inlining -finline -finline-atomics
# -finline-functions-called-once -finline-small-functions -fipa-cp
# -fipa-profile -fipa-pure-const -fipa-reference -fipa-sra
# -fira-hoist-pressure -fira-share-save-slots -fira-share-spill-slots
# -fivopts -fkeep-static-consts -fleading-underscore -fmath-errno
# -fmerge-constants -fmerge-debug-strings -fmove-loop-invariants
# -fomit-frame-pointer -foptimize-register-move -foptimize-sibling-calls
# -foptimize-strlen -fpartial-inlining -fpeephole -fpeephole2
# -fprefetch-loop-arrays -free -freg-struct-return -fregmove
# -freorder-blocks -freorder-functions -frerun-cse-after-loop
# -fsched-critical-path-heuristic -fsched-dep-count-heuristic
# -fsched-group-heuristic -fsched-interblock -fsched-last-insn-heuristic
# -fsched-rank-heuristic -fsched-spec -fsched-spec-insn-heuristic
# -fsched-stalled-insns-dep -fschedule-insns2 -fshow-column -fshrink-wrap
# -fsigned-zeros -fsplit-ivs-in-unroller -fsplit-wide-types
# -fstack-protector -fstrict-aliasing -fstrict-overflow
# -fstrict-volatile-bitfields -fsync-libcalls -fthread-jumps
# -ftoplevel-reorder -ftrapping-math -ftree-bit-ccp -ftree-builtin-call-dce
# -ftree-ccp -ftree-ch -ftree-coalesce-vars -ftree-copy-prop
# -ftree-copyrename -ftree-cselim -ftree-dce -ftree-dominator-opts
# -ftree-dse -ftree-forwprop -ftree-fre -ftree-loop-if-convert
# -ftree-loop-im -ftree-loop-ivcanon -ftree-loop-optimize
# -ftree-parallelize-loops= -ftree-phiprop -ftree-pre -ftree-pta
# -ftree-reassoc -ftree-scev-cprop -ftree-sink -ftree-slp-vectorize
# -ftree-slsr -ftree-sra -ftree-switch-conversion -ftree-tail-merge
# -ftree-ter -ftree-vect-loop-version -ftree-vrp -funit-at-a-time
# -funwind-tables -fvar-tracking -fvar-tracking-assignments -fverbose-asm
# -fzero-initialized-in-bss -m128bit-long-double -m64 -m80387
# -maccumulate-outgoing-args -malign-stringops -mfancy-math-387
# -mfp-ret-in-387 -mfxsr -mglibc -mieee-fp -mlong-double-80 -mmmx -mno-sse4
# -mpush-args -mred-zone -msse -msse2 -mtls-direct-seg-refs

	.text
.Ltext0:
	.p2align 4,,15
	.globl	_Z3addii
	.type	_Z3addii, @function
_Z3addii:
.LFB30:
	.file 1 "DissassemblyLab.cpp"
	.loc 1 2 0
	.cfi_startproc
.LVL0:
	.loc 1 3 0
	leal	(%rdi,%rsi), %eax	#, D.3059
	.loc 1 4 0
	ret
	.cfi_endproc
.LFE30:
	.size	_Z3addii, .-_Z3addii
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC0:
	.string	"%d"
	.section	.text.startup,"ax",@progbits
	.p2align 4,,15
	.globl	main
	.type	main, @function
main:
.LFB31:
	.loc 1 6 0
	.cfi_startproc
.LVL1:
	subq	$8, %rsp	#,
	.cfi_def_cfa_offset 16
.LBB6:
.LBB7:
	.file 2 "/usr/include/x86_64-linux-gnu/bits/stdio2.h"
	.loc 2 104 0
	movl	$4, %edx	#,
	movl	$.LC0, %esi	#,
	movl	$1, %edi	#,
	xorl	%eax, %eax	#
	call	__printf_chk	#
.LVL2:
.LBE7:
.LBE6:
	.loc 1 9 0
	xorl	%eax, %eax	#
	addq	$8, %rsp	#,
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE31:
	.size	main, .-main
	.text
.Letext0:
	.file 3 "/usr/lib/gcc/x86_64-linux-gnu/4.8/include/stddef.h"
	.file 4 "/usr/include/x86_64-linux-gnu/bits/types.h"
	.file 5 "/usr/include/stdio.h"
	.file 6 "/usr/include/libio.h"
	.file 7 "/usr/include/wchar.h"
	.file 8 "/usr/include/_G_config.h"
	.file 9 "/usr/include/c++/4.8/cstdio"
	.file 10 "/usr/include/x86_64-linux-gnu/bits/stdio.h"
	.file 11 "<built-in>"
	.section	.debug_info,"",@progbits
.Ldebug_info0:
	.long	0x78a
	.value	0x4
	.long	.Ldebug_abbrev0
	.byte	0x8
	.uleb128 0x1
	.long	.LASF88
	.byte	0x4
	.long	.LASF89
	.long	.LASF90
	.long	.Ldebug_ranges0+0
	.quad	0
	.long	.Ldebug_line0
	.uleb128 0x2
	.long	.LASF7
	.byte	0x3
	.byte	0xd4
	.long	0x34
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
	.byte	0x4
	.byte	0x83
	.long	0x65
	.uleb128 0x2
	.long	.LASF9
	.byte	0x4
	.byte	0x84
	.long	0x65
	.uleb128 0x3
	.byte	0x8
	.byte	0x7
	.long	.LASF10
	.uleb128 0x5
	.byte	0x8
	.uleb128 0x6
	.byte	0x8
	.long	0x91
	.uleb128 0x3
	.byte	0x1
	.byte	0x6
	.long	.LASF11
	.uleb128 0x2
	.long	.LASF12
	.byte	0x5
	.byte	0x30
	.long	0xa3
	.uleb128 0x7
	.long	.LASF52
	.byte	0xd8
	.byte	0x6
	.byte	0xf5
	.long	0x223
	.uleb128 0x8
	.long	.LASF13
	.byte	0x6
	.byte	0xf6
	.long	0x5e
	.byte	0
	.uleb128 0x8
	.long	.LASF14
	.byte	0x6
	.byte	0xfb
	.long	0x8b
	.byte	0x8
	.uleb128 0x8
	.long	.LASF15
	.byte	0x6
	.byte	0xfc
	.long	0x8b
	.byte	0x10
	.uleb128 0x8
	.long	.LASF16
	.byte	0x6
	.byte	0xfd
	.long	0x8b
	.byte	0x18
	.uleb128 0x8
	.long	.LASF17
	.byte	0x6
	.byte	0xfe
	.long	0x8b
	.byte	0x20
	.uleb128 0x8
	.long	.LASF18
	.byte	0x6
	.byte	0xff
	.long	0x8b
	.byte	0x28
	.uleb128 0x9
	.long	.LASF19
	.byte	0x6
	.value	0x100
	.long	0x8b
	.byte	0x30
	.uleb128 0x9
	.long	.LASF20
	.byte	0x6
	.value	0x101
	.long	0x8b
	.byte	0x38
	.uleb128 0x9
	.long	.LASF21
	.byte	0x6
	.value	0x102
	.long	0x8b
	.byte	0x40
	.uleb128 0x9
	.long	.LASF22
	.byte	0x6
	.value	0x104
	.long	0x8b
	.byte	0x48
	.uleb128 0x9
	.long	.LASF23
	.byte	0x6
	.value	0x105
	.long	0x8b
	.byte	0x50
	.uleb128 0x9
	.long	.LASF24
	.byte	0x6
	.value	0x106
	.long	0x8b
	.byte	0x58
	.uleb128 0x9
	.long	.LASF25
	.byte	0x6
	.value	0x108
	.long	0x2ea
	.byte	0x60
	.uleb128 0x9
	.long	.LASF26
	.byte	0x6
	.value	0x10a
	.long	0x2f0
	.byte	0x68
	.uleb128 0x9
	.long	.LASF27
	.byte	0x6
	.value	0x10c
	.long	0x5e
	.byte	0x70
	.uleb128 0x9
	.long	.LASF28
	.byte	0x6
	.value	0x110
	.long	0x5e
	.byte	0x74
	.uleb128 0x9
	.long	.LASF29
	.byte	0x6
	.value	0x112
	.long	0x6c
	.byte	0x78
	.uleb128 0x9
	.long	.LASF30
	.byte	0x6
	.value	0x116
	.long	0x42
	.byte	0x80
	.uleb128 0x9
	.long	.LASF31
	.byte	0x6
	.value	0x117
	.long	0x50
	.byte	0x82
	.uleb128 0x9
	.long	.LASF32
	.byte	0x6
	.value	0x118
	.long	0x2f6
	.byte	0x83
	.uleb128 0x9
	.long	.LASF33
	.byte	0x6
	.value	0x11c
	.long	0x306
	.byte	0x88
	.uleb128 0x9
	.long	.LASF34
	.byte	0x6
	.value	0x125
	.long	0x77
	.byte	0x90
	.uleb128 0x9
	.long	.LASF35
	.byte	0x6
	.value	0x12e
	.long	0x89
	.byte	0x98
	.uleb128 0x9
	.long	.LASF36
	.byte	0x6
	.value	0x12f
	.long	0x89
	.byte	0xa0
	.uleb128 0x9
	.long	.LASF37
	.byte	0x6
	.value	0x130
	.long	0x89
	.byte	0xa8
	.uleb128 0x9
	.long	.LASF38
	.byte	0x6
	.value	0x131
	.long	0x89
	.byte	0xb0
	.uleb128 0x9
	.long	.LASF39
	.byte	0x6
	.value	0x132
	.long	0x29
	.byte	0xb8
	.uleb128 0x9
	.long	.LASF40
	.byte	0x6
	.value	0x134
	.long	0x5e
	.byte	0xc0
	.uleb128 0x9
	.long	.LASF41
	.byte	0x6
	.value	0x136
	.long	0x30c
	.byte	0xc4
	.byte	0
	.uleb128 0xa
	.byte	0x8
	.byte	0x7
	.byte	0x53
	.long	.LASF47
	.long	0x267
	.uleb128 0xb
	.byte	0x4
	.byte	0x7
	.byte	0x56
	.long	0x24e
	.uleb128 0xc
	.long	.LASF42
	.byte	0x7
	.byte	0x58
	.long	0x49
	.uleb128 0xc
	.long	.LASF43
	.byte	0x7
	.byte	0x5c
	.long	0x267
	.byte	0
	.uleb128 0x8
	.long	.LASF44
	.byte	0x7
	.byte	0x54
	.long	0x5e
	.byte	0
	.uleb128 0x8
	.long	.LASF45
	.byte	0x7
	.byte	0x5d
	.long	0x22f
	.byte	0x4
	.byte	0
	.uleb128 0xd
	.long	0x91
	.long	0x277
	.uleb128 0xe
	.long	0x82
	.byte	0x3
	.byte	0
	.uleb128 0x2
	.long	.LASF46
	.byte	0x7
	.byte	0x5e
	.long	0x223
	.uleb128 0xa
	.byte	0x10
	.byte	0x8
	.byte	0x16
	.long	.LASF48
	.long	0x2a7
	.uleb128 0x8
	.long	.LASF49
	.byte	0x8
	.byte	0x17
	.long	0x6c
	.byte	0
	.uleb128 0x8
	.long	.LASF50
	.byte	0x8
	.byte	0x18
	.long	0x277
	.byte	0x8
	.byte	0
	.uleb128 0x2
	.long	.LASF51
	.byte	0x8
	.byte	0x19
	.long	0x282
	.uleb128 0xf
	.long	.LASF91
	.byte	0x6
	.byte	0x9a
	.uleb128 0x7
	.long	.LASF53
	.byte	0x18
	.byte	0x6
	.byte	0xa0
	.long	0x2ea
	.uleb128 0x8
	.long	.LASF54
	.byte	0x6
	.byte	0xa1
	.long	0x2ea
	.byte	0
	.uleb128 0x8
	.long	.LASF55
	.byte	0x6
	.byte	0xa2
	.long	0x2f0
	.byte	0x8
	.uleb128 0x8
	.long	.LASF56
	.byte	0x6
	.byte	0xa6
	.long	0x5e
	.byte	0x10
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x2b9
	.uleb128 0x6
	.byte	0x8
	.long	0xa3
	.uleb128 0xd
	.long	0x91
	.long	0x306
	.uleb128 0xe
	.long	0x82
	.byte	0
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x2b2
	.uleb128 0xd
	.long	0x91
	.long	0x31c
	.uleb128 0xe
	.long	0x82
	.byte	0x13
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x322
	.uleb128 0x10
	.long	0x91
	.uleb128 0x2
	.long	.LASF57
	.byte	0x5
	.byte	0x6e
	.long	0x2a7
	.uleb128 0x11
	.string	"std"
	.byte	0xb
	.byte	0
	.long	0x402
	.uleb128 0x12
	.byte	0x9
	.byte	0x60
	.long	0x98
	.uleb128 0x12
	.byte	0x9
	.byte	0x61
	.long	0x327
	.uleb128 0x12
	.byte	0x9
	.byte	0x63
	.long	0x402
	.uleb128 0x12
	.byte	0x9
	.byte	0x64
	.long	0x41a
	.uleb128 0x12
	.byte	0x9
	.byte	0x65
	.long	0x42f
	.uleb128 0x12
	.byte	0x9
	.byte	0x66
	.long	0x445
	.uleb128 0x12
	.byte	0x9
	.byte	0x67
	.long	0x45b
	.uleb128 0x12
	.byte	0x9
	.byte	0x68
	.long	0x470
	.uleb128 0x12
	.byte	0x9
	.byte	0x69
	.long	0x486
	.uleb128 0x12
	.byte	0x9
	.byte	0x6a
	.long	0x4a7
	.uleb128 0x12
	.byte	0x9
	.byte	0x6b
	.long	0x4c6
	.uleb128 0x12
	.byte	0x9
	.byte	0x6f
	.long	0x4e1
	.uleb128 0x12
	.byte	0x9
	.byte	0x70
	.long	0x506
	.uleb128 0x12
	.byte	0x9
	.byte	0x72
	.long	0x526
	.uleb128 0x12
	.byte	0x9
	.byte	0x73
	.long	0x546
	.uleb128 0x12
	.byte	0x9
	.byte	0x74
	.long	0x56c
	.uleb128 0x12
	.byte	0x9
	.byte	0x76
	.long	0x582
	.uleb128 0x12
	.byte	0x9
	.byte	0x77
	.long	0x598
	.uleb128 0x12
	.byte	0x9
	.byte	0x78
	.long	0x5a3
	.uleb128 0x12
	.byte	0x9
	.byte	0x79
	.long	0x5b9
	.uleb128 0x12
	.byte	0x9
	.byte	0x7e
	.long	0x5cb
	.uleb128 0x12
	.byte	0x9
	.byte	0x7f
	.long	0x5e0
	.uleb128 0x12
	.byte	0x9
	.byte	0x80
	.long	0x5fa
	.uleb128 0x12
	.byte	0x9
	.byte	0x82
	.long	0x60c
	.uleb128 0x12
	.byte	0x9
	.byte	0x83
	.long	0x623
	.uleb128 0x12
	.byte	0x9
	.byte	0x86
	.long	0x648
	.uleb128 0x12
	.byte	0x9
	.byte	0x87
	.long	0x653
	.uleb128 0x12
	.byte	0x9
	.byte	0x88
	.long	0x668
	.byte	0
	.uleb128 0x13
	.long	.LASF73
	.byte	0x5
	.value	0x33a
	.long	0x414
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x98
	.uleb128 0x15
	.long	.LASF58
	.byte	0x5
	.byte	0xed
	.long	0x5e
	.long	0x42f
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF59
	.byte	0x5
	.value	0x33c
	.long	0x5e
	.long	0x445
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF60
	.byte	0x5
	.value	0x33e
	.long	0x5e
	.long	0x45b
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x15
	.long	.LASF61
	.byte	0x5
	.byte	0xf2
	.long	0x5e
	.long	0x470
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF62
	.byte	0x5
	.value	0x213
	.long	0x5e
	.long	0x486
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF63
	.byte	0x5
	.value	0x31e
	.long	0x5e
	.long	0x4a1
	.uleb128 0x14
	.long	0x414
	.uleb128 0x14
	.long	0x4a1
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x327
	.uleb128 0x15
	.long	.LASF64
	.byte	0x2
	.byte	0xfd
	.long	0x8b
	.long	0x4c6
	.uleb128 0x14
	.long	0x8b
	.uleb128 0x14
	.long	0x5e
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF65
	.byte	0x5
	.value	0x110
	.long	0x414
	.long	0x4e1
	.uleb128 0x14
	.long	0x31c
	.uleb128 0x14
	.long	0x31c
	.byte	0
	.uleb128 0x16
	.long	.LASF66
	.byte	0x2
	.value	0x11a
	.long	0x29
	.long	0x506
	.uleb128 0x14
	.long	0x89
	.uleb128 0x14
	.long	0x29
	.uleb128 0x14
	.long	0x29
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF67
	.byte	0x5
	.value	0x116
	.long	0x414
	.long	0x526
	.uleb128 0x14
	.long	0x31c
	.uleb128 0x14
	.long	0x31c
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF68
	.byte	0x5
	.value	0x2ed
	.long	0x5e
	.long	0x546
	.uleb128 0x14
	.long	0x414
	.uleb128 0x14
	.long	0x65
	.uleb128 0x14
	.long	0x5e
	.byte	0
	.uleb128 0x16
	.long	.LASF69
	.byte	0x5
	.value	0x323
	.long	0x5e
	.long	0x561
	.uleb128 0x14
	.long	0x414
	.uleb128 0x14
	.long	0x561
	.byte	0
	.uleb128 0x6
	.byte	0x8
	.long	0x567
	.uleb128 0x10
	.long	0x327
	.uleb128 0x16
	.long	.LASF70
	.byte	0x5
	.value	0x2f2
	.long	0x65
	.long	0x582
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x16
	.long	.LASF71
	.byte	0x5
	.value	0x214
	.long	0x5e
	.long	0x598
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x17
	.long	.LASF80
	.byte	0xa
	.byte	0x2c
	.long	0x5e
	.uleb128 0x16
	.long	.LASF72
	.byte	0x5
	.value	0x27e
	.long	0x8b
	.long	0x5b9
	.uleb128 0x14
	.long	0x8b
	.byte	0
	.uleb128 0x13
	.long	.LASF74
	.byte	0x5
	.value	0x34e
	.long	0x5cb
	.uleb128 0x14
	.long	0x31c
	.byte	0
	.uleb128 0x15
	.long	.LASF75
	.byte	0x5
	.byte	0xb2
	.long	0x5e
	.long	0x5e0
	.uleb128 0x14
	.long	0x31c
	.byte	0
	.uleb128 0x15
	.long	.LASF76
	.byte	0x5
	.byte	0xb4
	.long	0x5e
	.long	0x5fa
	.uleb128 0x14
	.long	0x31c
	.uleb128 0x14
	.long	0x31c
	.byte	0
	.uleb128 0x13
	.long	.LASF77
	.byte	0x5
	.value	0x2f7
	.long	0x60c
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x13
	.long	.LASF78
	.byte	0x5
	.value	0x14c
	.long	0x623
	.uleb128 0x14
	.long	0x414
	.uleb128 0x14
	.long	0x8b
	.byte	0
	.uleb128 0x16
	.long	.LASF79
	.byte	0x5
	.value	0x150
	.long	0x5e
	.long	0x648
	.uleb128 0x14
	.long	0x414
	.uleb128 0x14
	.long	0x8b
	.uleb128 0x14
	.long	0x5e
	.uleb128 0x14
	.long	0x29
	.byte	0
	.uleb128 0x17
	.long	.LASF81
	.byte	0x5
	.byte	0xc3
	.long	0x414
	.uleb128 0x15
	.long	.LASF82
	.byte	0x5
	.byte	0xd1
	.long	0x8b
	.long	0x668
	.uleb128 0x14
	.long	0x8b
	.byte	0
	.uleb128 0x16
	.long	.LASF83
	.byte	0x5
	.value	0x2be
	.long	0x5e
	.long	0x683
	.uleb128 0x14
	.long	0x5e
	.uleb128 0x14
	.long	0x414
	.byte	0
	.uleb128 0x18
	.string	"add"
	.byte	0x1
	.byte	0x2
	.long	0x5e
	.byte	0x1
	.long	0x6a6
	.uleb128 0x19
	.string	"a"
	.byte	0x1
	.byte	0x2
	.long	0x5e
	.uleb128 0x19
	.string	"b"
	.byte	0x1
	.byte	0x2
	.long	0x5e
	.byte	0
	.uleb128 0x1a
	.long	.LASF92
	.byte	0x2
	.byte	0x66
	.long	0x5e
	.byte	0x3
	.long	0x6c3
	.uleb128 0x1b
	.long	.LASF84
	.byte	0x2
	.byte	0x66
	.long	0x31c
	.uleb128 0x1c
	.byte	0
	.uleb128 0x1d
	.long	0x683
	.long	.LASF93
	.quad	.LFB30
	.quad	.LFE30-.LFB30
	.uleb128 0x1
	.byte	0x9c
	.long	0x6f1
	.uleb128 0x1e
	.long	0x693
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1e
	.long	0x69c
	.uleb128 0x1
	.byte	0x54
	.byte	0
	.uleb128 0x1f
	.long	.LASF94
	.byte	0x1
	.byte	0x6
	.long	0x5e
	.quad	.LFB31
	.quad	.LFE31-.LFB31
	.uleb128 0x1
	.byte	0x9c
	.long	0x760
	.uleb128 0x20
	.long	0x6a6
	.quad	.LBB6
	.quad	.LBE6-.LBB6
	.byte	0x1
	.byte	0x7
	.uleb128 0x1e
	.long	0x6b6
	.uleb128 0xa
	.byte	0x3
	.quad	.LC0
	.byte	0x9f
	.uleb128 0x21
	.quad	.LVL2
	.long	0x776
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x55
	.uleb128 0x1
	.byte	0x31
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x54
	.uleb128 0x9
	.byte	0x3
	.quad	.LC0
	.uleb128 0x22
	.uleb128 0x1
	.byte	0x51
	.uleb128 0x1
	.byte	0x34
	.byte	0
	.byte	0
	.byte	0
	.uleb128 0x23
	.long	.LASF85
	.byte	0x5
	.byte	0xa8
	.long	0x2f0
	.uleb128 0x23
	.long	.LASF86
	.byte	0x5
	.byte	0xa9
	.long	0x2f0
	.uleb128 0x24
	.long	.LASF87
	.byte	0x2
	.byte	0x57
	.long	0x5e
	.uleb128 0x14
	.long	0x5e
	.uleb128 0x14
	.long	0x31c
	.uleb128 0x1c
	.byte	0
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
	.uleb128 0x55
	.uleb128 0x17
	.uleb128 0x11
	.uleb128 0x1
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
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x18
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
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x20
	.uleb128 0xb
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x19
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
	.byte	0
	.byte	0
	.uleb128 0x1a
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
	.uleb128 0x20
	.uleb128 0xb
	.uleb128 0x34
	.uleb128 0x19
	.uleb128 0x1
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x1b
	.uleb128 0x5
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
	.uleb128 0x1c
	.uleb128 0x18
	.byte	0
	.byte	0
	.byte	0
	.uleb128 0x1d
	.uleb128 0x2e
	.byte	0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x6e
	.uleb128 0xe
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
	.uleb128 0x1e
	.uleb128 0x5
	.byte	0
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x2
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x1f
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
	.uleb128 0x20
	.uleb128 0x1d
	.byte	0x1
	.uleb128 0x31
	.uleb128 0x13
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x12
	.uleb128 0x7
	.uleb128 0x58
	.uleb128 0xb
	.uleb128 0x59
	.uleb128 0xb
	.byte	0
	.byte	0
	.uleb128 0x21
	.uleb128 0x4109
	.byte	0x1
	.uleb128 0x11
	.uleb128 0x1
	.uleb128 0x31
	.uleb128 0x13
	.byte	0
	.byte	0
	.uleb128 0x22
	.uleb128 0x410a
	.byte	0
	.uleb128 0x2
	.uleb128 0x18
	.uleb128 0x2111
	.uleb128 0x18
	.byte	0
	.byte	0
	.uleb128 0x23
	.uleb128 0x34
	.byte	0
	.uleb128 0x3
	.uleb128 0xe
	.uleb128 0x3a
	.uleb128 0xb
	.uleb128 0x3b
	.uleb128 0xb
	.uleb128 0x49
	.uleb128 0x13
	.uleb128 0x3f
	.uleb128 0x19
	.uleb128 0x3c
	.uleb128 0x19
	.byte	0
	.byte	0
	.uleb128 0x24
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
	.byte	0
	.byte	0
	.byte	0
	.section	.debug_aranges,"",@progbits
	.long	0x3c
	.value	0x2
	.long	.Ldebug_info0
	.byte	0x8
	.byte	0
	.value	0
	.value	0
	.quad	.Ltext0
	.quad	.Letext0-.Ltext0
	.quad	.LFB31
	.quad	.LFE31-.LFB31
	.quad	0
	.quad	0
	.section	.debug_ranges,"",@progbits
.Ldebug_ranges0:
	.quad	.Ltext0
	.quad	.Letext0
	.quad	.LFB31
	.quad	.LFE31
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
.LASF87:
	.string	"__printf_chk"
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
.LASF94:
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
.LASF90:
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
.LASF93:
	.string	"_Z3addii"
.LASF1:
	.string	"unsigned char"
.LASF86:
	.string	"stdout"
.LASF43:
	.string	"__wchb"
.LASF4:
	.string	"signed char"
.LASF3:
	.string	"unsigned int"
.LASF53:
	.string	"_IO_marker"
.LASF32:
	.string	"_shortbuf"
.LASF35:
	.string	"__pad1"
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
.LASF89:
	.string	"DissassemblyLab.cpp"
.LASF54:
	.string	"_next"
.LASF9:
	.string	"__off64_t"
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
.LASF84:
	.string	"__fmt"
.LASF0:
	.string	"long unsigned int"
.LASF19:
	.string	"_IO_write_end"
.LASF88:
	.string	"GNU C++ 4.8.4 -mtune=generic -march=x86-64 -g -O2 -fstack-protector"
.LASF8:
	.string	"__off_t"
.LASF26:
	.string	"_chain"
.LASF47:
	.string	"11__mbstate_t"
.LASF23:
	.string	"_IO_backup_base"
.LASF85:
	.string	"stdin"
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
.LASF92:
	.string	"printf"
.LASF27:
	.string	"_fileno"
.LASF12:
	.string	"FILE"
.LASF50:
	.string	"__state"
.LASF66:
	.string	"fread"
.LASF83:
	.string	"ungetc"
.LASF60:
	.string	"ferror"
.LASF91:
	.string	"_IO_lock_t"
.LASF81:
	.string	"tmpfile"
	.ident	"GCC: (Ubuntu 4.8.4-2ubuntu1~14.04.4) 4.8.4"
	.section	.note.GNU-stack,"",@progbits
