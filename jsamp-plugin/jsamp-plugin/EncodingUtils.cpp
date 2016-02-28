/*
* JSAMP - Java implementation for SA:MP.
* Project started 13/4/15.
* Writted by spell <leandro.barbero122@gmail.com>
*/

#include "EncodingUtils.h"
#include <string.h>

bool isValidChar(char ch) {
	return (ch > 31 && ch < 127)
		|| ch == 'á'
		|| ch == 'é'
		|| ch == 'í'
		|| ch == 'ó'
		|| ch == 'ú'
		|| ch == 'ñ'
		|| ch == 'Á'
		|| ch == 'É'
		|| ch == 'Í'
		|| ch == 'Ó'
		|| ch == 'Ú'
		|| ch == 'Ñ'
		|| ch == '¿'
		|| ch == '¡';
}

void VerifyAndValidateInput(char *input) {
	unsigned int len = strlen(input);
	for (unsigned int i = 0; i < len; i++) {
		if (!isValidChar(input[i])) {
			input[i] = ' ';
		}
	}
}

void FixTextDrawStringAccents(char* string, int size) {
	for (int i = 0; i < size; i++) {
		switch (string[i]) {
			case 'à':   { string[i] = 151; break; }
			case 'á':   { string[i] = 152; break; }
			case 'â':   { string[i] = 153; break; }
			case 'ä':   { string[i] = 154; break; }
			case 'À':   { string[i] = 128; break; }
			case 'Á':   { string[i] = 129; break; }
			case 'Â':   { string[i] = 130; break; }
			case 'Ä':   { string[i] = 131; break; }
			case 'è':   { string[i] = 157; break; }
			case 'é':   { string[i] = 158; break; }
			case 'ê':   { string[i] = 159; break; }
			case 'ë':   { string[i] = 160; break; }
			case 'È':   { string[i] = 134; break; }
			case 'É':   { string[i] = 135; break; }
			case 'Ê':   { string[i] = 136; break; }
			case 'Ë':   { string[i] = 137; break; }
			case 'ì':   { string[i] = 161; break; }
			case 'í':   { string[i] = 162; break; }
			case 'î':   { string[i] = 163; break; }
			case 'ï':   { string[i] = 164; break; }
			case 'Ì':   { string[i] = 138; break; }
			case 'Í':   { string[i] = 139; break; }
			case 'Î':   { string[i] = 140; break; }
			case 'Ï':   { string[i] = 141; break; }
			case 'ò':   { string[i] = 165; break; }
			case 'ó':   { string[i] = 166; break; }
			case 'ô':   { string[i] = 167; break; }
			case 'ö':   { string[i] = 168; break; }
			case 'Ò':   { string[i] = 142; break; }
			case 'Ó':   { string[i] = 143; break; }
			case 'Ô':   { string[i] = 144; break; }
			case 'Ö':   { string[i] = 145; break; }
			case 'ù':   { string[i] = 169; break; }
			case 'ú':   { string[i] = 170; break; }
			case 'û':   { string[i] = 171; break; }
			case 'ü':   { string[i] = 172; break; }
			case 'Ù':   { string[i] = 146; break; }
			case 'Ú':   { string[i] = 147; break; }
			case 'Û':   { string[i] = 148; break; }
			case 'Ü':   { string[i] = 149; break; }
			case 'ñ':   { string[i] = 174; break; }
			case 'Ñ':   { string[i] = 173; break; }
			case '¡':   { string[i] = 64; break; }
			case '¿':   { string[i] = 175; break; }
			case '`':   { string[i] = 177; break; }
			case '&':   { string[i] = 38; break; }
		}
	}
}

/**
* Copyright (C) 2011 MK124
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

#ifdef _WINDLL

#include <Windows.h>

int mbs2wcs(unsigned int codepage, const char* src, int srclen, unsigned short* dst, int dstlen)
{
	if (srclen < 0) srclen = strlen(src);

	int ret = MultiByteToWideChar(codepage, MB_COMPOSITE, src, srclen, (LPWSTR)dst, dstlen - 1);
	dst[ret] = 0;
	return ret;
}

int wcs2mbs(unsigned int codepage, const unsigned short* src, int srclen, char* dst, int dstlen)
{
	if (srclen < 0) srclen = wcslen((LPCWSTR)src);

	BOOL usedDefaultChar = FALSE;
	int ret = WideCharToMultiByte(codepage, WC_COMPOSITECHECK, (LPCWSTR)src, srclen, dst, dstlen - 1, "?", &usedDefaultChar);

	if (usedDefaultChar && (codepage == 950 || codepage == 932))	// BIG5(TW) or SHIFT-JIS(JP)
	{
		wchar_t* convsrc = new wchar_t[srclen + 1];
		int rett = LCMapStringW(0x804, LCMAP_TRADITIONAL_CHINESE, (LPCWSTR)src, srclen, convsrc, srclen + 1);

		ret = WideCharToMultiByte(codepage, WC_COMPOSITECHECK, convsrc, rett, dst, dstlen - 1, "?", &usedDefaultChar);
		delete[] convsrc;
	}

	dst[ret] = 0;
	return ret;
}

#else

#include <iconv.h>
#include <string.h>
#include <map>
#include <string>

std::map<unsigned int, std::string> codepages;
std::map<unsigned int, std::string> codepageCaches;

int mbs2wcs(unsigned int codepage, const char* src, int srclen, unsigned short* dst, int dstlen)
{
	if (srclen == -1) srclen = (int)strlen(src);

	size_t inbytesleft = (size_t)srclen, outbytesleft = (dstlen - 1)*sizeof(unsigned short);
	char *in = (char*)src, *out = (char*)dst;

	int value = 1;
	iconv_t cd;
	if (codepageCaches.find(codepage) != codepageCaches.end())
	{
		cd = iconv_open("UTF-16LE", codepageCaches[codepage].c_str());
	}
	else
	{
		if (codepages.find(codepage) != codepages.end())
		{
			cd = iconv_open("UTF-16LE", codepages[codepage].c_str());
			codepageCaches[codepage] = codepages[codepage];
		}
		else
		{
			cd = iconv_open("UTF-16LE", "CP1252");
		}
	}
	iconvctl(cd, ICONV_SET_TRANSLITERATE, &value);
	iconvctl(cd, ICONV_SET_DISCARD_ILSEQ, &value);
	iconv(cd, &in, &inbytesleft, &out, &outbytesleft);
	iconv_close(cd);

	int len = (int)((dstlen - 1) - (outbytesleft / sizeof(unsigned short)));
	dst[len] = 0;
	return len;
}

int wcs2mbs(unsigned int codepage, const unsigned short *src, int srclen, char *dst, int dstlen)
{
	if (srclen == -1) srclen = (int)wcslen((wchar_t*)src);

	size_t inbytesleft = srclen*sizeof(unsigned short), outbytesleft = (size_t)(dstlen - 1);
	char *in = (char*)src, *out = dst;

	int value = 1;
	iconv_t cd;
	if (codepageCaches.find(codepage) != codepageCaches.end())
	{
		cd = iconv_open(codepageCaches[codepage].c_str(), "UTF-16LE");
	}
	else
	{
		if (codepages.find(codepage) != codepages.end())
		{
			cd = iconv_open(codepages[codepage].c_str(), "UTF-16LE");
			codepageCaches[codepage] = codepages[codepage];
		}
		else
		{
			cd = iconv_open("CP1252", "UTF-16LE");
		}
	}
	iconvctl(cd, ICONV_SET_TRANSLITERATE, &value);
	iconvctl(cd, ICONV_SET_DISCARD_ILSEQ, &value);
	iconv(cd, &in, &inbytesleft, &out, &outbytesleft);
	iconv_close(cd);

	int len = (int)((dstlen - 1) - outbytesleft);
	dst[len] = 0;
	return len;
}

#endif
