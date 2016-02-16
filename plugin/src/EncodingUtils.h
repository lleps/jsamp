/*
* JSAMP - Java implementation for SA:MP.
* Project started 13/4/15.
* Writted by spell <leandro.barbero122@gmail.com>
*/

void VerifyAndValidateInput(char* input);
void FixTextDrawStringAccents(char* string, int size);
int mbs2wcs(unsigned int codepage, const char* src, int srclen, unsigned short* dst, int dstlen);
int wcs2mbs(unsigned int codepage, const unsigned short *src, int srclen, char *dst, int dstlen);
