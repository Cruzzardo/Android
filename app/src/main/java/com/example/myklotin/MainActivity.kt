package com.example.myklotin


import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random


class MainActivity() : AppCompatActivity(), Parcelable {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        texto = findViewById(R.id.txvInfo)
        texto.text = ""

    }

    var indi = 0;
    var indicator = 0;
    var animals = arrayOfNulls<Animal>(6)
    private lateinit var texto: TextView

    constructor(parcel: Parcel) : this() {
        indicator = parcel.readInt()
        indi = parcel.readInt()
    }


    class Animal(i: Int, x: Int) {
        var one = false
        var name: String = ""
        var age: String = ""
        var sound: String? = null
        var breed: String = ""
        var two = 0

        init {
            if (i < 3) one = true
            this.two = x;
            setAll()
        }

        private fun setAll() {
            var dates = arrayOfNulls<String>(3)
            if (one)
                dates = newDates2()
            else
                dates = newDates();
                dates[0]?.let { this.name = it }
                dates[1]?.let { this.age = it }

            if (dates[2] === "woooof")
                this.sound = null
            else
                this.sound = dates[2]
            dates[3]?.let { this.breed = it }
        }

        private fun newDates(): Array<String?> {
            var dates = arrayOfNulls<String>(4)
            var numRandom = Random.nextInt(1, 4);
            while (numRandom == two) {
                numRandom = Random.nextInt(1, 4);
            }
            two = numRandom
            when (numRandom) {
                1 -> {
                    dates[0] = "Pancho";
                    dates[1] = "2 años";
                    dates[2] = "woooof";
                    dates[3] = "Pastor Aleman"
                }
                2 -> {
                    dates[0] = "Fade out";
                    dates[1] = "5 años";
                    dates[2] = "woooof";
                    dates[3] = "Pastor Belga"
                }
                3 -> {
                    dates[0] = "Minino";
                    dates[1] = "6 meses";
                    dates[2] = "Miau";
                    dates[3] = "Gato Persa"
                }
                else -> {
                }
            }
            return dates;
        }

        private fun newDates2(): Array<String?> {
            var dates = arrayOfNulls<String>(4)
            var numRandom = Random.nextInt(1, 4);
            while (numRandom == two) {
                numRandom = Random.nextInt(1, 4);
            }
            two = numRandom
            when (numRandom) {
                1 -> {
                    dates[0] = "Asesino";
                    dates[1] = "6 años";
                    dates[2] = "Miau";
                    dates[3] = "Bengala"
                }
                2 -> {
                    dates[0] = "Firulais";
                    dates[1] = "8 años";
                    dates[2] = "woooof";
                    dates[3] = "PitBul"
                }
                3 -> {
                    dates[0] = "Virgen";
                    dates[1] = "3 meses";
                    dates[2] = "Miau";
                    dates[3] = "Maine Coon"
                }
                else -> {
                }
            }
            return dates;
        }
    }

    fun setTexto(i: Int) {
        var name: String = ""
        var age: String = ""
        var sound: String = ""
        animals[i]?.let { it.name?.let { name = it } }
        animals[i]?.let {
            it.age?.let { age = it }
            animals[i]?.let { it.sound?.let { sound = it } }
            var s = "Information of the Animals\n" +
                    "Name: ${name} \n" +
                    "Age: ${age}\n" +
                    "Sound: ${sound}\n"
            texto.text = s
        }
    }

    fun Name(view: View) {
        var breed: String = ""
        animals[indi]?.let { it.name?.let { breed = it } }
        toast("Name: ${breed}")
    }

    fun Sound(view: View) {
        var breed: String? = null
        animals[indi]?.let { it.sound?.let { breed = it } }

        if (breed != null) {
            toast("Miau")
        } else
            toast("Woooof")
    }

    fun toast(mess: String) {
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
    }

    fun Breed(view: View) {
        var breed: String = ""
        animals[indi]?.let { it.breed?.let { breed = it } }
        toast("Breed: ${breed}")
    }

    fun Back(view: View) {
        if (indi >= 1) indi--

        setTexto(indi)
    }

    fun New(view: View) {
        if (indicator == 3) toast("Nueva Lista de Mascotas")
        if (indicator < 6) {
            if (indicator > 0) {
                var x = 0
                animals[indicator - 1]?.let { x = it.two }
                animals[indicator] = Animal(indicator, x)
            } else animals[indicator] = Animal(indicator, 0)
            indi = indicator
            indicator++
            setTexto(indi)
        } else toast("Solo se puede tener 6")

    }

    fun Next(view: View) {
        if (indi < 6)
            indi++
        else {
        }
        if (indi < indicator)
            setTexto(indi);
        else
            indi--
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(indicator)
        parcel.writeInt(indi)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }
}