package file

import (
	"crypto/sha1"
	"io"
	"os"
)

// Hash performs an SHA1 hash of the file present at the given path.
func Hash(path string) ([]byte, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}

	defer file.Close()

	hash := sha1.New()
	if _, err = io.Copy(hash, file); err != nil {
		return nil, err
	}

	result := hash.Sum(nil)
	return result, nil
}
