from setuptools import setup
import os
import sys

VENV_FOLDER = 'venv'

NAME = 'basic-python-app'
DESCRIPTION = 'Just a basic python app'
URL = 'https://github.com/starter/basic-python-app'
EMAIL = 'enoch@notanemail.com'
AUTHOR = 'Enoch Chu'
REQUIRES_PYTHON = '>=3.6.0'
VERSION = '0.1.0'

# Packages you want your build to export
local_install = []

packages = [
   'enoch'
]

# Required dependencies
required = [ ]

test_requirements = [
   'pytest',
   'nose'
]

def remove_venv():
    print('Remove venv')
    os.system(f"rm -R { VENV_FOLDER } ")

def setup_venv():
    print('Setting up virtualenv')
    os.system(f"python -m virtualenv { VENV_FOLDER }")
    os.system(f"{ os.path.abspath(f'./{VENV_FOLDER}/Scripts/python') } -m pip install --upgrade pip")

def setup_build_system():
    print('Installing wheel')
    os.system(f"{ os.path.abspath(f'./venv/Scripts/pip') } install wheel")

def setup_local_dependencies():
    print('Installing local dependencies')
    for package in local_install:
        os.system(f"{os.path.abspath(f'./venv/Scripts/pip')} install { os.path.abspath(package) }")

def setup_dependencies():
    print('Installing required dependencies')
    for package in required:
        os.system(f"{os.path.abspath(f'./venv/Scripts/pip')} install { package }")

def setup_test_dependencies():
    print('Installing test dependencies')
    for package in test_requirements:
        os.system(f"{os.path.abspath(f'./venv/Scripts/pip')} install { package }")

def build_all():
    setup_venv()
    setup_build_system()
    setup_local_dependencies()
    setup_dependencies()
    setup_test_dependencies()

## Setup
if sys.argv[-1] == 'setup':
    build_all()
    sys.exit()

## Setup
if sys.argv[-1] == 'install-dependencies':
    setup_local_dependencies()
    setup_dependencies()
    setup_test_dependencies()
    sys.exit()

## Clean
if sys.argv[-1] == 'clean':
    remove_venv()
    sys.exit()

## Build
if sys.argv[-1] == 'build':
    os.system('python setup.py sdist bdist_wheel')
    sys.exit()

setup(
    name=NAME,
    version=VERSION,
    description=DESCRIPTION,
    author=AUTHOR,
    author_email=EMAIL,
    python_requires=REQUIRES_PYTHON,
    url=URL,
    packages=packages,
    install_requires= required,
    tests_require = test_requirements
)
