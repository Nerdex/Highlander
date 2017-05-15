﻿using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace GameLauncher
{
    class Utils
    {
        public static string computeMD5(string file)
        {
            MD5 md5 = MD5.Create();

            string hash = "";
            using (var stream = File.OpenRead(file))
            {
                hash = BitConverter.ToString(md5.ComputeHash(stream)).Replace("-", "");
            }
            return hash;
        }
    }
}
