from mcresources import ResourceManager, clean_generated_resources
import assets


def main():
    rm = ResourceManager('betterfoliage', resource_dir='../src/main/resources')

    generate_all(rm)
    print('New = %d, Modified = %d, Unchanged = %d, Errors = %d' % (rm.new_files, rm.modified_files, rm.unchanged_files, rm.error_files))


def generate_all(rm: ResourceManager):
    assets.generate(rm)

    rm.flush()


if __name__ == '__main__':
    main()
