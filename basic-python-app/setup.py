from setuptools import setup

import os
import sys

NAME = 'basic-python-app'
DESCRIPTION = 'Just a basic python app'
URL = 'https://github.com/starter/basic-python-app'
EMAIL = 'enoch@notanemail.com'
AUTHOR = 'Enoch Chu'
REQUIRES_PYTHON = '>=3.6.0'
VERSION = '0.1.0'

# Setup
if sys.argv[-1] == 'initial-setup':
    os.system('python -m virtualenv venv')
    print('Remember to pip install wheel if you want to build this package!')
    sys.exit()

# Build
if sys.argv[-1] == 'build':
    os.system('python setup.py sdist bdist_wheel')
    sys.exit()

packages = ['enoch']

install_requires = []

test_required = [
    'coverage',
    'pytest',
    'pytest-watch'
]

setup(
    name=NAME,
    version=VERSION,
    description=DESCRIPTION,
    author=AUTHOR,
    author_email=EMAIL,
    python_requires=REQUIRES_PYTHON,
    url=URL,
    packages=packages,
    install_requires=install_requires,
    test_required=test_required,
    license='MIT'
)
