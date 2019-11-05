import sys
from cx_Freeze import setup, Executable

setup(
    name = "JSON reader",
    version = "1.0",
    description = "JSON reader tool",
    executables = [Executable("jsonLoader.py", base = "Win32GUI")])