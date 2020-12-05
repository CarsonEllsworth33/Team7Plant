/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.data

class dummyStats{

    companion object{

        fun createDataSet(): ArrayList<statBlock>{
            val list = ArrayList<statBlock>()
            list.add(
                    statBlock(
                            "Temperature",
                            "75",
                            "https://e7.pngegg.com/pngimages/341/867/png-clipart-white-check-with-green-background-illustration-fingerprint-comcast-circle-symbol-technology-tick-miscellaneous-angle.png",
                    )
            )
            list.add(
                    statBlock(
                            "Acidity",
                            "8",
                            "https://uxwing.com/wp-content/themes/uxwing/download/01-user_interface/red-x.png",
                    )
            )

            list.add(
                    statBlock(
                            "Moisture",
                            "20",
                            "https://e7.pngegg.com/pngimages/341/867/png-clipart-white-check-with-green-background-illustration-fingerprint-comcast-circle-symbol-technology-tick-miscellaneous-angle.png",
                    )
            )
            return list
        }
    }
}