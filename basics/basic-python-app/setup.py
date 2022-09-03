#!/usr/bin/env python3

from os import path
from setuptools import setup

NAME = "basic-python-app"
AUTHOR = "Enoch Chu"
EMAIL = ""
URL = ""
VERSION = "0.0.1"
DESCRIPTION= "Yet another python app"

PYTHON_REQUIRES=">=3.6"

PACKAGES = ['']

INSTALL_REQUIRES = [
    'requests'
]

SETUP_REQUIRES = [
    'pytest-runner',
    'wheel'
]

TESTS_REQUIRE = [
    'pytest'
]

with open(path.join(path.abspath(path.dirname(__file__)), "README.md")) as readmeFile:
    long_description = readmeFile.read()

setup(
    name=NAME,
    version=VERSION,
    description=DESCRIPTION,
    long_description=long_description,
    long_description_content_type='text/markdown',
    author=AUTHOR,
    author_email=EMAIL,
    url="",
    install_requires=INSTALL_REQUIRES,
    packages=PACKAGES,
    setup_requires=SETUP_REQUIRES,
    tests_require=TESTS_REQUIRE,
    python_requires=PYTHON_REQUIRES,
    classifiers=[],
)
