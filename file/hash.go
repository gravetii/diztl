package file

import (
	"crypto/sha1"
	"io"
	"os"
	"time"

	"github.com/gravetii/diztl/diztl"
)

// Hash performs an SHA1 hash of the file present at the given path.
func Hash(path string) (*diztl.FileHash, error) {
	file, err := os.Open(path)
	if err != nil {
		return nil, err
	}

	defer file.Close()

	hash := sha1.New()
	if _, err = io.Copy(hash, file); err != nil {
		return nil, err
	}

	checksum := hash.Sum(nil)
	result := &diztl.FileHash{Checksum: checksum, Ts: time.Now().Unix()}
	return result, nil
}
