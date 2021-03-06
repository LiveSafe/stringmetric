package com.rockymadden.stringmetric.cli.similarity

import com.rockymadden.stringmetric.cli._
import com.rockymadden.stringmetric.similarity.LevenshteinMetric

case object levenshteinmetric extends Command(
	(opts) =>
		"Compares the number of characters that two strings are different from one another via insertion, deletion, " +
		"and substitution." + Ls + Ls +
		"Syntax:" + Ls +
		Tab + "levenshteinmetric [Options] string1 string2..." + Ls + Ls +
		"Options:" + Ls +
		Tab + "-h, --help" + Ls +
		Tab + Tab + "Outputs description, syntax, and options.",
	(opts) => opts.contains('dashless) && (opts('dashless): Array[String]).length == 2,
	(opts) => {
		val strings: Array[String] = opts('dashless)

		LevenshteinMetric.compare(strings(0), strings(1))
			.map(_.toString)
			.getOrElse("not comparable")
	}
) { override def main(args: Array[String]): Unit = super.main(args) }
