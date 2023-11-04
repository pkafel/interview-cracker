package com.piotrkafel.jpmorgan

class VersionComparator {

    companion object {

        fun getLatest(ver1: String, ver2: String): String {
            val ver1Split = getVersionParts(ver1)
            val ver2Split = getVersionParts(ver2)

            for (i in 0..3) {
                if(ver1Split[i] > ver2Split[i]) return ver1
                if(ver2Split[i] > ver1Split[i]) return ver2
            }

            return "same"
        }

        private fun getVersionParts(version: String): Array<String> {
            val versionParts = version.split(".")

            val majorVersion = versionParts[0]
            val minorVersion = if(versionParts.size >= 2) versionParts[1] else "0"
            val patch = if(versionParts.size >= 3) versionParts[2] else "0"

            val patchParts = patch.split("-")
            val patchWithoutSuffix = patchParts[0]
            val patchSuffix = if(patchParts.size == 2) patchParts[1] else ""

            return arrayOf(majorVersion, minorVersion, patchWithoutSuffix, patchSuffix)
        }
    }
}