//Jack Henderson jhh0139

int endian(int num)
{
   int b0, b1, b2, b3;

    b0 = (num & 0x000000ff) >> 0; //move byte 0 to position byte 3
    b1 = (num & 0x0000ff00) >> 8; //move byte 1 to position byte 2
    b2 = (num & 0x00ff0000) >> 16; //move byte 2 to position byte 1
    b3 = (num & 0xff000000) >> 24; //move byte 3 to position byte 0


    return((b0 << 24)|(b1 << 16)|(b2 << 8)|(b3 < 0 ));
}

