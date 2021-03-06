/*
* JSAMP - Java implementation for SA:MP.
* Project started 13/4/15.
* Writted by spell <leandro.barbero122@gmail.com>
*/

#include "EncodingUtils.h"
#include <string.h>

bool isValidChar(char ch) {
	return (ch > 31 && ch < 127)
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�'
		|| ch == '�';
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
	unsigned char* ustring = (unsigned char*)string;
	for (int i = 0; i < size; i++) {
		switch (string[i]) {
			case '�':   { ustring[i] = 151; break; }
			case '�':   { ustring[i] = 152; break; }
			case '�':   { ustring[i] = 153; break; }
			case '�':   { ustring[i] = 154; break; }
			case '�':   { ustring[i] = 128; break; }
			case '�':   { ustring[i] = 129; break; }
			case '�':   { ustring[i] = 130; break; }
			case '�':   { ustring[i] = 131; break; }
			case '�':   { ustring[i] = 157; break; }
			case '�':   { ustring[i] = 158; break; }
			case '�':   { ustring[i] = 159; break; }
			case '�':   { ustring[i] = 160; break; }
			case '�':   { ustring[i] = 134; break; }
			case '�':   { ustring[i] = 135; break; }
			case '�':   { ustring[i] = 136; break; }
			case '�':   { ustring[i] = 137; break; }
			case '�':   { ustring[i] = 161; break; }
			case '�':   { ustring[i] = 162; break; }
			case '�':   { ustring[i] = 163; break; }
			case '�':   { ustring[i] = 164; break; }
			case '�':   { ustring[i] = 138; break; }
			case '�':   { ustring[i] = 139; break; }
			case '�':   { ustring[i] = 140; break; }
			case '�':   { ustring[i] = 141; break; }
			case '�':   { ustring[i] = 165; break; }
			case '�':   { ustring[i] = 166; break; }
			case '�':   { ustring[i] = 167; break; }
			case '�':   { ustring[i] = 168; break; }
			case '�':   { ustring[i] = 142; break; }
			case '�':   { ustring[i] = 143; break; }
			case '�':   { ustring[i] = 144; break; }
			case '�':   { ustring[i] = 145; break; }
			case '�':   { ustring[i] = 169; break; }
			case '�':   { ustring[i] = 170; break; }
			case '�':   { ustring[i] = 171; break; }
			case '�':   { ustring[i] = 172; break; }
			case '�':   { ustring[i] = 146; break; }
			case '�':   { ustring[i] = 147; break; }
			case '�':   { ustring[i] = 148; break; }
			case '�':   { ustring[i] = 149; break; }
			case '�':   { ustring[i] = 174; break; }
			case '�':   { ustring[i] = 173; break; }
			case '�':   { ustring[i] = 64; break; }
			case '�':   { ustring[i] = 175; break; }
			case '`':   { ustring[i] = 177; break; }
			case '&':   { ustring[i] = 38; break; }
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
