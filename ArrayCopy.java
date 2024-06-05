// erstellt am 08.10.2023
public class ArrayCopy {
	
	// Ausgeführt an Test-Cases, welche folgendes abdecken:
	

/**

* Method to copy objects from the array src into the array dst. Before copying

* the method checks that all elements in the range [ srcIndex... srcIndex+ length-1] lie

* within src, furthermore that all elements in the range [ dstIndex... dstIndex+ length-1]

* lie within dst. If these conditions are not met, copy throws an

* ArrayIndexOutOfBoundsException and does neither modify src nor dst.

*

* Furthermore, copy checks that all elements from src can be assigned into dst,

* if these conditions are not met, copy throws a ClassCastException and

* does neither modify src nor dst.

**

Copying inside the same array must be supported, from smaller to larger

* indices and from larger to smaller indices. In this case, copy

* must ensure that the data contained in the partial array src[ srcIndex... srcIndex+ length-1]

* before copying is exactly identical to the data contained in the partial array

* dst[ dstIndex... dstIndex+ length-1] after the copy.

* @param src Array of objects from which the data is copied, must be unmodified unless dst == src

* @param srcIndex Index of first object to be copied from

* @param dst Array of objects from which the data is copied, may be identical

* to src, must be unmodified in case an exception is thrown.

* @param dstIndex Index of first object to be copied to

* @param length Number of elements to be copied.

*/
	


public static void copy(Object[] src, int srcIndex, Object[] dst, int dstIndex, int length) {



//Wertebereiche für OOB-Exception prüfen

checkRanges(src.length, srcIndex, dst.length, dstIndex, length);



//Prüfen ob Elemente von Quell- zu Ziel kopierbar, i.e. kompatibel miteinander

for (int i = 0; i < length; i++) {

if (src[i] != null && !dst.getClass().getComponentType().isAssignableFrom(src[i].getClass())) {

throw new ClassCastException();

}

}


//Kopieroperation Quell zu Zielarray via System-interne Methode

System.arraycopy(src, srcIndex, dst, dstIndex, length);

}



/**

* Method to copy ints from the array src into the byte array dst. Before copying

* the method checks that all elements in the range [ srcIndex... srcIndex+ length-1] lie

* within src, furthermore that all elements in the range [ dstIndex... dstIndex+ length-1]

* lie within dst. If these conditions are not met, copy throws an

* ArrayIndexOutOfBoundsException and does neither modify src nor dst.

**

Furthermore, if check is set to true, then copy guarantees that all elements from

* src can be assigned into dst without losing information, if this condition is not

* met, copy throws an IllegalArgumentException and does neither modify src nor dst.

**

Copying in either direction must be supported, from smaller to larger

* indices and from larger to smaller indices. In this case, copy

* must ensure that the data contained in the partial array src[ srcIndex... srcIndex+ length-1]

* before copying is exactly identical to the data contained in the partial array

* dst[ dstIndex... dstIndex+ length-1] after the copy.

* @param src Array of objects from which the data is copied, must be unmodified unless dst == src

* @param srcIndex Index of first object to be copied from

* @param dst Array of objects from which the data is copied, may be identical

* to src, must be unmodified in case an exception is thrown.

* @param dstIndex Index of first object to be copied to

* @param length Number of elements to be copied.

* @param check Flag indicating that the range of elements must be checked.

*/

public static void copy(int[] src, int srcIndex, byte[] dst, int dstIndex, int length, boolean check) {


//wieder Wertebereiche prüfen

checkRanges(src.length, srcIndex, dst.length, dstIndex, length);

if (check) {

for (int i = srcIndex; i < srcIndex + length; i++) {

int value = src[i];


// ist alles valide für Byte-Werte?

if (value < Byte.MIN_VALUE || value > Byte.MAX_VALUE) {

throw new IllegalArgumentException("kein valider Byte-Wert");

                                                }

}

}



//Byte-Array dient als Zwischenspeicher zum copy-pasten

byte[] temp = new byte[src.length];



//int-Elemente aus src auf Byte casten, via for-loop in Zwischenspeicher

for (int i = 0; i < length; i++) {

temp[i] = (byte) src[i];

                                }



//von Zwischenspeicher zu Zielarray

System.arraycopy(temp, srcIndex, dst, dstIndex, length);

}

/**

* Method to copy bytes from the array src into the int array dst.

* Before copying the method checks that all elements in the range

* [ srcIndex... srcIndex+ length-1] lie within src, furthermore

* that all elements in the range [ dstIndex... dstIndex+ length-1]

* lie within dst. If these conditions are not met, copy throws an

* ArrayIndexOutOfBoundsException and does neither modify src nor dst.

**

Furthermore, if sign is set to true, then copy preserves the sign of

* the source value, otherwise all target values are created by

* prepending 0- bits to the source bit pattern.

**

* Copying in either direction must be supported, from smaller to larger

* indices and from larger to smaller indices. In this case, copy

* must ensure that the data contained in the partial array src

* [ srcIndex... srcIndex+ length-1] before copying is exactly

* identical to the data contained in the partial array dst

* [ dstIndex... dstIndex+ length-1] after the copy.

* @param src Array of objects from which the data is copied, must be unmodified unless

* dst == src

* @param srcIndex Index of first object to be copied from

* @param dst Array of objects from which the data is copied, may be identical

* to src, must be unmodified in case an exception is thrown.

* @param dstIndex Index of first object to be copied to

* @param length Number of elements to be copied.

* @param sign Flag indicating that the sign of elements must be preserved.

*/

public static void copy(byte[] src, int srcIndex, int[] dst, int dstIndex, int length, boolean sign) {

//Wertebereich-Check #3

checkRanges(src.length, srcIndex, dst.length, dstIndex, length);



//Ziwschenspeicher-Array; so groß wie zu kopierende Elemente

int[] temp = new int[length];



if (sign) {
for (int i = 0; i < length; i++) {
// if sign == true => direkt kopieren
temp[i] = src[i];

          }

} else {

for (int i = 0; i < length; i++) {

// if sign == false => byte zu 'unsigned' int

int tempVal = Byte.toUnsignedInt(src[i]);

temp[i] = tempVal;

}

}



//Kopieroperation

System.arraycopy(temp, srcIndex, dst, dstIndex, length);

}



/**

* Private method to check array ranges. You can implement it, but you are

* not required to do so.

*/

private static void checkRanges(int srcLength, int srcIndex, int dstLength, int dstIndex, int length) {

//sämtliche Wege outOfBounds zu kommen abdecken

if (srcIndex < 0 || dstIndex < 0 || (srcIndex + length > srcLength) || (dstIndex + length > dstLength) || length < 0) {

throw new ArrayIndexOutOfBoundsException("Wert ist nicht gültig für das vorliegende Array");

}

}

}
